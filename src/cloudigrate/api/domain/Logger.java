package cloudigrate.api.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
		   private static Logger logger = null;
		   BufferedWriter out = null;
		   static FileWriter fstream = null;
		   String writeInput = null;
		   Date start = null;
		   Date end = null;
		   long timeStamp = 0;
		   
		   protected Logger() {
		      // Exists only to defeat instantiation.
		   }
		   
		   public static Logger getInstance() {
		      if(logger == null) {
		    	  logger = new Logger();
		      }
		      return logger;
		   }
		   
		   public void writeLogger(String user, String applicationName, String method, String platform,  String service, String level)
		   {
			   timeStamp = this.getEnd().getTime() - this.getStart().getTime();
			   writeInput = user + "," + method + ',' + platform + ',' + level + ',' + this.getStart() + ',' + this.getEnd() + ',' + timeStamp + ',' + applicationName+ ',' + service  ; 
			   try  
			   {
				   fstream = new FileWriter("/tmp/log.txt", true);
				   out = new BufferedWriter(fstream);
			       System.out.println(writeInput);
			      out.write("\n"+ writeInput);
			       //out.write(writeInput);
			       writeInput = "";
			   }
			   catch (IOException e)
			   {
			       System.err.println("Error: " + e.getMessage());
			   }
			   finally
			   {
			       if(out != null) {
			           try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       }
			   }
		   }

		public Date getStart() {
			return start;
		}

		public void setStart(Date start) {
			this.start = start;
		}

		public Date getEnd() {
			return end;
		}

		public void setEnd(Date end) {
			this.end = end;
		}
		   
}
