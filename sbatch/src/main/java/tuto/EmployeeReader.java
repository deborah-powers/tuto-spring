package tuto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import model.EmployeeSrc;
import model.EmployeeSrcList;
import utils.DataFileProperties;

@Slf4j
@Component
public class EmployeeReader implements ItemReader<EmployeeSrcList>{
	// première méthode pour récupérer les données de application.properties
	@Value("${data.folder}")
	private String dataFolder;
	@Value("${data.src}")
	private String dataFileName;
	// deuxième méthode pour récupérer les données de application.properties
	@Autowired
	private DataFileProperties dataFile;
	private boolean firstTime = true;

	@Override
	public EmployeeSrcList read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException{
		return readEmployeeFile(dataFileName);
	}

	private EmployeeSrcList readEmployeeFile(String fileName){
		if (firstTime) {
			firstTime = false;
			try {
				FileReader fileReader = new FileReader(dataFile.getSrcPath());
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				EmployeeSrcList employees = new EmployeeSrcList();
				String line;
				while ((line = bufferedReader.readLine()) != null)
					employees.add(convertFromString(line));
				bufferedReader.close();
				return employees;
			} catch (FileNotFoundException exception) {
				log.error("le fichier n'a pas été trouvé: " + dataFile.getSrc());
				return null;
			} catch (IOException exception) {
				log.error("le fichier est illisible: " + dataFile.getSrc());
				return null;
			}
		} else
			return null;
	}

	private EmployeeSrc convertFromString(String data){
		EmployeeSrc employee = new EmployeeSrc();
		employee.fromCsvLine(data);
		return employee;
	}
}
