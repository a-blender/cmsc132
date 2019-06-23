//SUBMIT SERVER by Anna Blendermann
package submitserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

import sun.net.www.URLConnection;

//SUBMIT SERVER CLASS*****************************************************
//creates submitserver for students and their project scores
//submits data through threads and different webpage connections
public class SubmitServer 
{
	//hashmap with string keys and inner hashmap values
	private HashMap<String, HashMap<Integer, Submission>> map;
	private int numProjects;

	public SubmitServer(int numProjects) 
	{
		map = new HashMap<String, HashMap<Integer, Submission>>();

		if (numProjects >= 1) //project num must be valid
			this.numProjects = numProjects;
	}

	//ADD SUBMISSION METHOD***********************************************
	//adds new student name and submission object to hashmap
	public SubmitServer addSubmission(String name, int projectNumber, int score) 
	{
		synchronized(this) //prevent submission data races
		{
			if (name != null && !name.equals("") && projectNumber >= 1 && 
					projectNumber <= numProjects && score >= 0 && score <= 100)
			{
				if (map.containsKey(name)) //student exists
				{
					HashMap<Integer, Submission> innerMap = map.get(name);
					Submission submission = new Submission(projectNumber, score);
					innerMap.put(innerMap.size()+1, submission);
				}
				else //adding a new student
				{
					HashMap<Integer, Submission> innerMap = new HashMap<Integer, Submission>();
					Submission submission = new Submission(projectNumber, score);
					innerMap.put(1, submission); //add submission
					map.put(name, innerMap); //add new student
				}
			}
		}
		return this;
	}

	//NUM SUBMISSIONS 1**************************************************
	//returns number of submissions for target student and target project
	public int numSubmissions(String name, int projectNumber) 
	{
		int count = 0;
		
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects)
		{
			for (String student : map.keySet())
			{
				if (student.equals(name)) //student found
				{
					for (Submission object : map.get(student).values())
					{
						if (object.projectNumber == projectNumber)
							count += 1; //project/score found
					}
				}	
			}
		}
		return count;
	}

	//NUM SUBMISSIONS 2**************************************************
	//returns number of submissions for target project 
	public int numSubmissions(int projectNumber) 
	{
		int count = 0;
		if (projectNumber >= 1 && projectNumber <= numProjects)
		{
			for (String student : map.keySet())
			{
				for (Submission object : map.get(student).values())
				{
					if (object.projectNumber == projectNumber)
						count += 1; //project/score found
				}	
			}
		}
		return count;
	}

	//NUM SUBMISSIONS 3**************************************************
	//returns number of submissions for target student name
	public int numSubmissions(String name) 
	{
		if (name != null && !name.equals(""))
		{
			for (String student : map.keySet())
			{
				if (student.equals(name)) //student found
					return map.get(student).values().size();
			}
		}
		return 0; //return zero if student not found
	}

	//NUM SUBMISSIONS 4**************************************************
	//returns number of TOTAL submissions on the server
	public int numSubmissions() 
	{
		int totalProjects = 0;

		for (String student : map.keySet())
			totalProjects += map.get(student).values().size();

		return totalProjects;
	}

	//BEST SUBMISSION SCORE**********************************************
	//returns the best score that the target student has submitted
	//for the target project - returns most recent in case of duplicates
	public int bestSubmissionScore(String name, int projectNumber) 
	{
		int bestScore = 0; //returns zero by default
		if (name == null || name.equals("") || projectNumber < 1 
				|| projectNumber > numProjects)
			return -1;
		else //parameters are valid
		{
			for (String student : map.keySet())
			{
				if (student.equals(name)) //student found
				{
					for (Submission object : map.get(student).values())
					{
						if (object.projectNumber == projectNumber
								&& object.score >= bestScore)
							bestScore = object.score; //found best score
					}
				}	
			}
		}
		return bestScore;	
	}

	//SATISFACTORY METHOD************************************************
	//attempts to find existing score for student/project number
	public boolean satisfactory(String name, int projectNumber) 
	{
		if (name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects)
		{
			for (String student : map.keySet())
			{
				if (student.equals(name)) //student found
				{
					for (Submission object : map.get(student).values())
					{
						if (object.projectNumber == projectNumber)
						{
							if(object.score > 0)
								return true; //good submission found
						}
					}
				}	
			}
		}
		return false; //satisfactory submission not found
	}

	//GOT EXTRA CREDIT METHOD********************************************
	//determines whether student got extra credit on target project
	public boolean gotExtraCredit(String name, int projectNumber) 
	{
		if (name != null && !name.equals("") && projectNumber >= 1 
				&& projectNumber <= numProjects)
		{
			for (String student : map.keySet())
			{
				if (student.equals(name)) //student found
				{
					if (map.get(student).size() == 1 &&
							map.get(student).get(1).score == 100)
						return true; //only score must be 100
				}	
			}
		}
		return false; //multiple submissions were found
	}

	//READ SUBMISSIONS METHOD********************************************
	//uses threads to simultaneously read server submissions
	public void readSubmissions(String[] urls)
	{
		ArrayList<Thread> list = new ArrayList<Thread>();
		if (urls.length != 0)
		{
			for (String data : urls) //one thread for each url
				list.add(new Thread(new createThread(data)));
		}

		for (Thread thread : list)
			thread.start(); //start all threads

		try 
		{
			for (Thread thread : list)
				thread.join(); //join all threads
		} 
		catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "InterruptedException");
			e.printStackTrace(); }

	} //end of readSubmissions method


	//CONVERT URL TO STRING METHOD***************************************
	//helper method to extract relevant data from html files
	public ArrayList<String> convertURLToString(BufferedReader read)
	{
		//convert the array of html files to strings
		ArrayList<String> list = new ArrayList<String>();
		String str = ""; //new data string

		try 
		{
			while ((str = read.readLine()) != null)
			{
				if (str.contains("<tr>")) //process line
				{
					for (int x = 0; x < str.length(); x++)
					{
						for (int y = x; y < str.length(); y++)
						{
							String sub = str.substring(x,y);
							if (sub.startsWith(">") && sub.endsWith("<"))
							{
								String target = sub.substring(1, sub.length()-1);
								if (!target.isEmpty() && !target.contains(">"))
									list.add(target.trim()); //found target data
							}
						}
					}
				}
				else continue; //irrelevant line
			}
			read.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException"); }
		return list;
	} //end of convert url to string method


	//CREATE THREAD INNER CLASS******************************************
	//createThread creates and runs Threads for SubmitServer objects
	public class createThread implements Runnable
	{
		String data = "";
		public createThread(String data)
		{
			this.data = data; //set url inside class
		}	

		//RUN METHOD*****************************************************
		//runs one thread with corresponding webpage connection
		//uses convertURLToString() to extract data from url connection
		//submits relevant data to current submitSever object
		public void run() 
		{
			try 
			{	
				URL object = new URL(data);
				HttpURLConnection conn = (HttpURLConnection) object.openConnection();

				BufferedReader read = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				ArrayList<String> list = convertURLToString(read);
				ArrayList<String> data = new ArrayList<String>();

				for (String str : list) //remove irrelevant elements
				{
					if (!str.equals("") && !str.equals("Student")
							&& !str.equals("Project") && !str.equals("Score"))
						data.add(str);
				}

				for (int x = 0; x < data.size(); x += 3) //add server data
				{
					addSubmission(data.get(x), Integer.valueOf(data.get(x+1)),
							Integer.valueOf(data.get(x+2)));
				}
			} 
			catch (MalformedURLException e) {
				JOptionPane.showMessageDialog(null, "MalformedURLException detected");
				e.printStackTrace(); }

			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IOException detected");
				e.printStackTrace(); }
		} //end of run method

	} //end of innerThread class

} //end of class
