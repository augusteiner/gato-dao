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
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void main(String[] args) throws SQLException {

        /**

           CREATE TABLE gato (

             gato_id INT AUTO_INCREMENT,
             nome VARCHAR(100),

             PRIMARY KEY (gato_id)

           );

         */

        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1/easy_tour?serverTimezone=America/Fortaleza&useSSL=false",
            "easy_tour",
            "123456");

        // XXX Restaurando o próximo id pra 1 (apaga os dados da tabela)
        connection.createStatement().execute("TRUNCATE TABLE gato");

        GatoDAO dao = new GatoDAO(connection);

        Gato gato1;
        Gato outroGato;
        Gato outroGato2;

        gato1 = new Gato(1, "Bichano");
        outroGato = new Gato(2, "Bichano II");

        dao.inserir(gato1);
        dao.inserir(outroGato);

        outroGato.setNome("Rex");

        dao.atualizar(outroGato);

        dao.removerPorId(1);

        outroGato2 = dao.recuperarPorId(outroGato.getId());

        System.out.println(outroGato2.getNome());

    }

}
