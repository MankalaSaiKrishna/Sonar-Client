package com.pramati;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.sonar.wsclient.services.TimeMachine;
import org.sonar.wsclient.services.TimeMachineCell;
import org.sonar.wsclient.services.TimeMachineQuery;

public class SonarClient {
	public final String propertiesFile = "sonar-server-config.properties";
	public String projectKey;
	public void retrieveCodeChurnMeasures(){
		Sonar sonar = null;
		try{
			//Establish Connection with Sonar Server
			sonar = establishConnectionWithSonar();
			//In order to retrieve metrics of a project,pass the parameters - "Project Key" and "Metric" keys which are needed
			//Time Machine API
			TimeMachine t = sonar.find(TimeMachineQuery.createForMetrics(projectKey, "lines","complexity","class_complexity","file_complexity","statements","classes","files"));
			TimeMachineCell eachCell = null;
			TimeMachineCell[] allCells= t.getCells();
			for(int i=0 ; i<allCells.length ; i++){ // for each Version/Analysis
				eachCell = allCells[i];
				System.out.println(eachCell.getDate()); // Version Date
				Object[] allValues = eachCell.getValues();
				for(int j=0 ; j < allValues.length ; j++ ){//for each Metric specified					
					System.out.println(allValues[j]); // Metric Values				
				}			
			}				
		}
		catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public Sonar establishConnectionWithSonar(){
		//Input is fetched from properties file
		Properties prop = new Properties();
		String sonarServerURL = "";
		String userName = "";
		String pwd = "";
		Sonar sonar = null;
		try{
			prop.load(new FileInputStream(propertiesFile));
			sonarServerURL = prop.getProperty("sonar-server");
			userName = prop.getProperty("sonar-user");
			pwd = prop.getProperty("sonar-pwd");
			projectKey = prop.getProperty("sonar-project-key");
			sonar = new Sonar(new HttpClient4Connector(new Host(sonarServerURL,userName,pwd)));
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return sonar;
	}
	
	public static void main(String params[]){
		SonarClient sc = new SonarClient();
		sc.retrieveCodeChurnMeasures();
	}
}
