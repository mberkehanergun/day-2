package mainpackage.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class NamedParamJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    public void fillTableFromCsv(String csvFilePath) {
        String tableName = "CUSTOMER";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                
                String fieldValue = line.trim();

                String sql = "INSERT INTO " + tableName + " (NAMEANDSURNAME) VALUES (:fieldValue)";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }

            System.out.println("Data from the CSV file has been successfully inserted into the table.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}