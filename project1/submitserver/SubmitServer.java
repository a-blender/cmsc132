//SUBMIT SERVER by Anna Blendermann
//Honor Pledge - I have not received help on this assignment
package submitserver;
import java.util.ArrayList;

//SUBMIT SERVER CLASS******************************************************
//submitserver class stores and manipulates two lists of data, one for student
//names and one for their corresponding submissions
public class SubmitServer 
{
	//Two arraylist will represent the submit server database
	//one arraylist for the submitted names and one for corresponding scores
	private ArrayList<String> names_list = new ArrayList<String>();
	private ArrayList<Integer> scores_list = new ArrayList<Integer>();

	//ADD SUBMISSION METHOD************************************************
	//addSubmission adds one student submission to the same index of both
	//arraylists - name to names_list and score to scores_list
	public SubmitServer addSubmission(String name, int score) 
	{
		if(!name.isEmpty() && (score>=0 && score<=100)) 
		{
			names_list.add(name);
			scores_list.add(score);
		} 
		return this;
	}

	//NUM SUBMISSIONS METHOD***********************************************
	//numSubmissions interates the first arraylist, finds the number of times
	//the target student is listed on the server, and returns that number
	public int numSubmissions(String name) 
	{
		int numSubmissions = 0;
		if(name == null)
			return -1;
		else //name is valid
		{
			for(int x=0; x<names_list.size(); x++) 
			{
				if(names_list.get(x).equals(name))
					numSubmissions++;
			}
		}
		return numSubmissions;	
	}

	//NUM SUBMISSIONS METHOD*************************************************
	//numSubmissions returns the total number of submissions to the server
	public int numSubmissions() 
	{
		return names_list.size(); 
	}

	//BEST SUBMISSION NUMBER METHOD******************************************
	//bestSubmissionNumber checks if the target student exists in the server
	//and if so, iterates through a separate arraylist of their grades to find the best
	public int bestSubmissionNumber(String name) 
	{
		ArrayList<Integer> grades = new ArrayList<Integer>();
		int bestNumber = 0, bestIndex = 0;

		if(!names_list.contains(name) || name==null)
			return -1;
		else //server contains target student
		{
			//finds all grades from the target student
			for(int x=0; x<names_list.size(); x++) 
			{
				if(names_list.get(x).equals(name))
					grades.add(scores_list.get(x));
			}
			//finds the best grade from the target student
			for(int x=0; x<grades.size(); x++) 
			{
				if(grades.get(x)>=bestNumber)
				{
					bestNumber = grades.get(x);
					bestIndex = x+1; 
				}
			}
		}
		return bestIndex;
	} //end of method

	//BEST SUBMISSION SCORE METHOD*******************************************
	//bestSubmissionScore uses the bestSubmissionNumber to find the index of the
	//best submission from the target student
	public int bestSubmissionScore(String name) 
	{
		ArrayList<Integer> grades = new ArrayList<Integer>();
		int bestIndex = bestSubmissionNumber(name)-1;

		if(!names_list.contains(name) || name==null)
			return -1;
		else //server contains target student
		{
			//finds all grades from the target student
			for(int x=0; x<names_list.size(); x++) 
			{
				if(names_list.get(x).equals(name))
					grades.add(scores_list.get(x));
			}
			return grades.get(bestIndex);
		}
	}

	//STATISFACTORY METHOD***************************************************
	//satisfactory checks both arraylists for a submission greater than zero
	//from the target student (if student exists)
	public boolean satisfactory(String name) 
	{	
		if(!names_list.contains(name) || name==null)
			return false;
		else //server contains target student
		{
			for(int x=0; x<names_list.size(); x++)
			{
				if(names_list.get(x).equals(name))
				{
					if(scores_list.get(x)>0)
						return true;
				}
			}
		}
		return false;
	}

	//GOT EXTRA CREDIT METHOD*************************************************
	//gotExtraCredit accumulates students with grades of 100 and then removes
	//duplicates from that subset arraylist, returns arraylist size
	public int gotExtraCredit() 
	{
		ArrayList<Integer> studentIndex = new ArrayList<Integer>();

		//puts students with grades of 100 into studentIndex arraylist
		for(int x=0; x<names_list.size(); x++)
		{
			if(scores_list.get(x)==100)
				studentIndex.add(x);
		}
		//removes students with more than 1 submission
		for (int x=0; x<studentIndex.size(); x++) 
		{
			for (int y= x+1; y<studentIndex.size(); y++) 
			{
				if (studentIndex.get(x).equals(studentIndex.get(y)))
				{
					studentIndex.remove(x);
					studentIndex.remove(y);
				}
			}
		}
		return studentIndex.size();
	}

	//CHANGE SCORE METHOD*****************************************************
	//changeScore changes the score of a target student based upon their
	//submission number, which is different from the index
	public boolean changeScore(String name, int submissionNumber, int newScore) 
	{
		int num = 0;
		for(int x=0; x<names_list.size(); x++)
		{
			if(names_list.get(x).equals(name))
			{
				num = num+1;
				if(num==submissionNumber)
				{
					scores_list.set(x, newScore);
					return true;
				}
			}
		}	
		return false;
	}
} //end of SubmitServer class