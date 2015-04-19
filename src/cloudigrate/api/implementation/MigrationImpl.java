package cloudigrate.api.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MigrationImpl {

	String privatePath = "/usr/local/mysql/bin/mysqldump";
	String awsPath = "cloudigrate.c3fher2linu7.us-west-1.rds.amazonaws.com";
	String googlePath = "173.194.254.105";

	public void migrateSQL(String value) {
		//Send backup to localhost
		//String restoreBackupCommand[] = {"tcsh","-c", "/usr/local/mysql/bin/mysql -h localhost -u ashutosh -pcloudigrate --port=3306 < /tmp/mysql-dump.sql"};

		//Take backup from AWS
		//String createBackupCommand = "/usr/local/mysql/bin/mysqldump -hcloudigrate.c3fher2linu7.us-west-1.rds.amazonaws.com -ucloudigrate -pcloudigrate --port=3306 --single-transaction --routines --triggers --databases cloudigrate -r /tmp/mysql-dump.sql";


		//Take backup from Google
		//String createBackupCommand = "/usr/local/mysql/bin/mysqldump -h173.194.254.105 -uroot -proot --port=3306 --single-transaction --routines --triggers --databases cloudigrate -r /tmp/mysql-dump.sql";

		//Send backup to AWS
		//String restoreBackupCommand[] = {"tcsh","-c", "/usr/local/mysql/bin/mysql -h cloudigrate.c3fher2linu7.us-west-1.rds.amazonaws.com -u cloudigrate -pcloudigrate --port=3306 < /tmp/mysql-dump.sql"};

		//Send backup to Google
		//String restoreBackupCommand[] = {"tcsh","-c", "/usr/local/mysql/bin/mysql -h 173.194.254.105 -u root -proot --port=3306 < /tmp/mysql-dump.sql"};


		// Taking backup from source database
		//String createBackupOutput = executeCreateBackupCommand(createBackupCommand);		
		//System.out.println(createBackupOutput);

		// Restoring backup data to destination
		//String restoreBackupOutput = executeRestoreBackupCommand(restoreBackupCommand);		
		//System.out.println(restoreBackupOutput);

		if(value.equals("AWS"))
		{
			// Google to AWS
			//Take backup from Google
			String createBackupCommand = "/usr/local/mysql/bin/mysqldump -h173.194.254.105 -ucloudigrate -pcloudigrate --port=3306 --single-transaction --routines --triggers --databases cloudigrate -r /tmp/mysql-dump.sql";
			//Send backup to AWS
			String restoreBackupCommand[] = {"tcsh","-c", "/usr/local/mysql/bin/mysql -h cloudigrate.c7babyv2jgia.us-west-2.rds.amazonaws.com -u cloudigrate -pcloudigrate --port=3306 < /tmp/mysql-dump.sql"};

			// Taking backup from source database
			String createBackupOutput = executeCreateBackupCommand(createBackupCommand);		
			System.out.println(createBackupOutput);

			// Restoring backup data to destination
			String restoreBackupOutput = executeRestoreBackupCommand(restoreBackupCommand);		
			System.out.println(restoreBackupOutput);

		}else
		{
			// AWS to Google
			//Take backup from AWS
			String createBackupCommand = "/usr/local/mysql/bin/mysqldump -hcloudigrate.c7babyv2jgia.us-west-2.rds.amazonaws.com -ucloudigrate -pcloudigrate --port=3306 --single-transaction --routines --triggers --databases cloudigrate -r /tmp/mysql-dump.sql";
			//Send backup to Google
			String restoreBackupCommand[] = {"tcsh","-c", "/usr/local/mysql/bin/mysql -h 173.194.254.105 -u cloudigrate -pcloudigrate --port=3306 < /tmp/mysql-dump.sql"};

			// Taking backup from source database
			String createBackupOutput = executeCreateBackupCommand(createBackupCommand);		
			System.out.println(createBackupOutput);

			// Restoring backup data to destination
			String restoreBackupOutput = executeRestoreBackupCommand(restoreBackupCommand);		
			System.out.println(restoreBackupOutput);


		}
	}

	public void migrateNOSQL(String value) {
		// TODO Auto-generated method stub

	}

	public void migrateInstance(String value) {
		// TODO Auto-generated method stub

	}

	public void migrateStorage(String value) {
		// TODO Auto-generated method stub

	}

	private String executeCreateBackupCommand(String command) {
		StringBuffer output = new StringBuffer();
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			int exitval=process.waitFor();
			System.out.println("Backup exitval: "+exitval);
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();

	}

	private String executeRestoreBackupCommand(String command[]) {
		StringBuffer output = new StringBuffer();
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			int exitval=process.waitFor();
			System.out.println("Restore exitval: "+exitval);
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
