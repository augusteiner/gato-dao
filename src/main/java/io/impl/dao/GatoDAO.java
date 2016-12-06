/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class GatoDAO {

    private Connection connection;

    public GatoDAO(Connection connection) {

        this.connection = connection;
    }

    public void atualizar(Gato gato) throws SQLException {

        String sql = "UPDATE gato SET nome = ? WHERE gato_id = ?";
        PreparedStatement ps = this.getConnection().prepareStatement(sql);

        ps.setString(1, gato.getNome());
        ps.setInt(2, gato.getId());

        ps.execute();

    }

    private Connection getConnection() {

        return this.connection;

    }

    public void inserir(Gato gato) throws SQLException {

        String sql = "INSERT INTO gato (nome) VALUES (?)";
        PreparedStatement ps = this.getConnection().prepareStatement(sql);

        ps.setString(1, gato.getNome());

        ps.execute();

    }

    public Gato recuperarPorId(int id) throws SQLException {

        String sql = "SELECT nome FROM gato WHERE gato_id = ?";
        PreparedStatement ps = this.getConnection().prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Gato gato = new Gato();

            gato.setId(id);
            gato.setNome(rs.getString("nome"));

            return gato;

        } else {

            return null;

        }

    }

    public void removerPorId(int id) throws SQLException {

        String sql = "DELETE FROM gato WHERE gato_id = ?";
        PreparedStatement ps = this.getConnection().prepareStatement(sql);

        ps.setInt(1, id);

        ps.execute();

    }

}
