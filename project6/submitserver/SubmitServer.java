//SUBMIT SERVER by Anna Blendermann
package submitserver;
import java.util.HashMap;

//SUBMIT SERVER CLASS*****************************************************
public class SubmitServer 
{
	//hashmap with string keys and inner hashmap values
	private HashMap<String, HashMap<Integer, Submission>> map;
	private int numProjects;

	public SubmitServer(int numProjects) 
	{
		map = new HashMap<String, HashMap<Integer, Submission>>();

		if(numProjects >= 1) //project num must be valid
			this.numProjects = numProjects;
	}

	//ADD SUBMISSION METHOD***********************************************
	//adds new student name and submission object to hashmap
	public SubmitServer addSubmission(String name, int projectNumber, int score) 
	{
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects && score >= 0 && score <= 100)
		{
			if(map.containsKey(name)) //student exists
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
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					for(Submission object : map.get(student).values())
					{
						if(object.projectNumber == projectNumber)
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
		if(projectNumber >= 1 && projectNumber <= numProjects)
		{
			for(String student : map.keySet())
			{
				for(Submission object : map.get(student).values())
				{
					if(object.projectNumber == projectNumber)
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
		if(name != null && !name.equals(""))
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
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

		for(String student : map.keySet())
			totalProjects += map.get(student).values().size();

		return totalProjects;
	}

	//BEST SUBMISSION NUMBER METHOD**************************************
	//returns the submission number of the student's best score
	public int bestSubmissionNumber(String name, int projectNumber) 
	{
		int bestScore = 0; //best score of the student
		int bestNumber = 0; //submission number of the best score
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects)
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					for(Integer key : map.get(student).keySet())
					{
						if(map.get(student).get(key).projectNumber == projectNumber
								&& map.get(student).get(key).score >= bestScore)
						{
							bestScore = map.get(student).get(key).score;
							bestNumber = key; //found key of best score
						}
					}
				}	
			}
		}
		return bestNumber;	
	}

	//BEST SUBMISSION SCORE**********************************************
	//returns the best score that the target student has submitted
	//for the target project - returns most recent in case of duplicates
	public int bestSubmissionScore(String name, int projectNumber) 
	{
		int bestScore = 0; //returns zero by default
		if(name == null || name.equals("") || projectNumber < 1 ||  
				projectNumber > numProjects)
			return -1;
		else //parameters are valid
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					for(Submission object : map.get(student).values())
					{
						if(object.projectNumber == projectNumber
								&& object.score >= bestScore)
							bestScore = object.score; //found best score
					}
				}	
			}
		}
		return bestScore;	
	}

	//SATISFACTORY METHOD************************************************
	public boolean satisfactory(String name, int projectNumber) 
	{
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects)
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					for(Submission object : map.get(student).values())
					{
						if(object.projectNumber == projectNumber)
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
	public boolean gotExtraCredit(String name, int projectNumber) 
	{
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects)
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					if(map.get(student).size() == 1 &&
							map.get(student).get(1).score == 100)
						return true; //only submitted score must be 100
				}	
			}
		}
		return false; //multiple submissions were found
	}

	//CHANGE SCORE METHOD************************************************
	//test this one pretty thoroughly
	public boolean changeScore(String name, int projectNumber,
			int submissionNumber, int newScore) 
	{
		if(name != null && !name.equals("") && projectNumber >= 1 && 
				projectNumber <= numProjects && newScore >= 0 && newScore <= 100)
		{
			for(String student : map.keySet())
			{
				if(student.equals(name)) //student found
				{
					if(map.get(student).values().size() >= submissionNumber)
					{
						Submission object = map.get(student).get(submissionNumber);
						if(object.projectNumber == projectNumber)
						{
							if(object.score != newScore) //check for duplicates
							{
								object.score = newScore; //change score
								return true; //score has been changed
							}
						}
					}
				}	
			}
		}
		return false; //invalid values detected
	}
}
