/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique. If not, see <http://www.gnu.org/licenses/>.
 */
package mysql5;

import com.aionemu.commons.database.DB;
import com.aionemu.commons.database.DatabaseFactory;
import com.aionemu.commons.database.ParamReadStH;
import com.aionemu.gameserver.dao.AccountSielEnergyDAO;
import com.aionemu.gameserver.model.account.AccountSielEnergy;
import com.aionemu.gameserver.model.account.SielEnergyType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySQL5AccountSielEnergyDAO extends AccountSielEnergyDAO {

    private static final Logger log = LoggerFactory.getLogger(MySQL5AccountSielEnergyDAO.class);

    private static final String INSERT_SIEL = "INSERT INTO accounts_siel (account_id, id, start, end) VALUES (?, ?, ?, ?)";
    private static final String LOAD_SIEL = "SELECT * FROM accounts_siel WHERE account_id=?";
    private static final String REMOVE_SIEL = "DELETE FROM accounts_siel WHERE id=2";

    public void load(final Player player) {
        DB.select(LOAD_SIEL, new ParamReadStH() {
            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, player.getPlayerAccount().getId());
            }

            @Override
            public void handleRead(ResultSet rset) throws SQLException {
                while (rset.next()) {
                    AccountSielEnergy sielEnergy = new AccountSielEnergy(
                            SielEnergyType.getSielTypeById(rset.getInt("id")),
                            rset.getTimestamp("start"),
                            rset.getTimestamp("end")
                    );
                    player.setAccountSielEnergy(sielEnergy);
                }
            }
        });
    }

    @Override
    public boolean add(Player player) {
        Connection con = null;
        try {
            con = DatabaseFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT_SIEL);
            AccountSielEnergy sielEnergy = player.getAccountSielEnergy();
            stmt.setInt(1, player.getPlayerAccount().getId());
            stmt.setInt(2, sielEnergy.getType().getId());
            stmt.setTimestamp(3, sielEnergy.getStart());
            stmt.setTimestamp(4, sielEnergy.getEnd());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            log.error("Store Siel Energy", e);
            return false;
        } finally {
            DatabaseFactory.close(con);
        }
    }

    @Override
    public void remove() {
        PreparedStatement statement = DB.prepareStatement(REMOVE_SIEL);
        DB.executeUpdateAndClose(statement);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(String s, int i, int i1) {
		return MySQL5DAOUtils.supports(s, i, i1);
	}
}
