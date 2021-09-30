/*
 * Copyright (C) 2020-2021  G. Arslan
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms and conditions of the GNU General Public License,
 * version 2, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.enums;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    @Getter
    private Properties properties = new Properties();
    private String path = "src/main/resources/";

    /**
     * PropertiesLoader for *.properties-Files
     * @param isWithLangCode,
     */
    public PropertiesLoader(String file, boolean isWithLangCode) {
        String gameLang = "";
        if (isWithLangCode) gameLang = System.getProperty("user.language").equals("de") ? ".de" : ".en";

        try (InputStream input = new FileInputStream(path + file + gameLang + ".properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
