import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
public class Module1
{
	static String relations = "";
	StringBuffer sb = new StringBuffer(40);
	 static String swap(String str, int i, int j) 
    { 
        StringBuilder sb = new StringBuilder(str); 
        sb.setCharAt(i, str.charAt(j)); 
        sb.setCharAt(j, str.charAt(i)); 
        return sb.toString(); 
    }
	 //remove character at position p in string str
	static String charRemoveAt(String str, int p,int n) 
	{  
		if(p+1!=n){
			if(p!=0)

              return str.substring(0, p-1) + str.substring(p + 1,n-1); 
			else
				return str.substring(p+1,n-1);

          }
              else
              return str.substring(0,p-1); 
           }  
	// check if all the characters of s1 are present in s2
	static int isSubstring(String s1, String s2) 
	{ 
	    int M = s1.length(); 
	    int N = s2.length(); 
	    int i,j;
	    int flag;
		for(i=0;i<M;i++)
		{
			flag=0;
			for(j=0;j<N;j++)
			{
				if(s1.charAt(i)==s2.charAt(j))
				{
					flag=1;
					break;
				}
			}
			if(flag!=1)
			{
				return -1;
			}
		 } 
	    return 0; 
	} 
	//checks if a character is present in string s1
	static int isPresent(String s1, char a)
	{
		int i;
		for(i=0;i<s1.length();i++)
		{
			if(s1.charAt(i)==a)
			{
				return 1;
			}
		}
		return 0;
	}
	//removes a character present al position l in character array
	static void remove(int l,char minclo[],int len)
	{
		int j,k;
		for(j=l;j<len-1;j++)
		{
			minclo[j]=minclo[j+1];
		}
	}
	//checks if a given string can result to the inclusion of all the attributes
	static void checkPrimaryKeys(int n,int m,String s,String closure[],int present[],String s1[],Vector<String> vec,Vector<String> vec2)
	{
		int[] present2=new int[26];
		int min=100,i,j,k,l;
		String s4=s;
		for(i=0;i<26;i++)
		{
			present2[i]=0;
		}
			String ans;
			ans=s4;
			int flag=0;
		for(j=0;j<100;j++)
		{
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],ans)!=-1)
				{
					for(l=0;l<closure[k].length();l++)
					{
						if(isPresent(ans,closure[k].charAt(l))==0)
						{
							String temo=Character.toString(closure[k].charAt(l));
							ans=ans+temo;
						}
					}
				}
			}
		}
			for(j=0;j<ans.length();j++)
			{
				int temp4=ans.charAt(j)-'A';
				if(present2[temp4]!=1)
				present2[temp4]++;
			}
			for(j=0;j<26;j++)
			{
				if(present2[j]!=present[j])
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				System.out.println(s4);	
				vec.add(s4);
				vec2.add(s4);
			}		
	}
	//creates all possible combinations of a string and then check for the primary key
        static void powerSet(String str, int index, String curr,int n,int m,String closure[],String s1[],int present[],Vector<String> vec,Vector<String> vec2) 
		{  
		  
		    // base case 
		    if (index == str.length()) { 
		          checkPrimaryKeys(n,m,curr,closure,present,s1,vec,vec2);
		    } 
		  
		    // Two cases for every character 
		    // (i) We consider the character 
		    // as part of current subset 
		    // (ii) We do not consider current 
		    // character as part of current 
		    // subset 
		    if(index!=str.length())
		    powerSet(str, index + 1, curr + Character.toString(str.charAt(index)),n,m,closure,s1,present,vec,vec2); 
		if(index!=str.length())
		    powerSet(str, index + 1, curr,n,m,closure,s1,present,vec,vec2); 
		}
        //checks if a string is the superset of the give string so that it can be passed as a primary key
		static void powerSet(String str, int index, String curr , String s2) 
		{ 
		    int n = str.length(); 
		  
		    // base case 
		    if (index == n) { 
		         
		        if(isSubstring(s2,curr)==0)
		        {
		        	System.out.println(curr);
		        	relations += (curr + "\n");
		        } 
		    } 
		  
		    // Two cases for every character 
		    // (i) We consider the character 
		    // as part of current subset 
		    // (ii) We do not consider current 
		    // character as part of current 
		    // subset 
		    if(index!=n)
		    powerSet(str, index + 1, curr + str.charAt(index),s2); 
		if(index!=n)
		    powerSet(str, index + 1, curr,s2); 
		}
		//marks all characters present in s1 and minclo only
	static String filter(String s,String s1,char minclo[],int len)
	{
		int i,j,k,flag;
		for(i=0;i<s.length();i++)
		{
			flag=0;
			for(j=0;j<s1.length();j++)
			{
				if(s1.charAt(j)==s.charAt(i))
				{
					flag=1;
				}
			}
			for(j=0;j<len;j++)
			{
				if(minclo[j]==s.charAt(i))
				{
					flag=1;
				}
			}
			if(flag==0)
			{
				String temo=s.replace(Character.toString(s.charAt(i)),"");
				s=temo;
				i=-1;
			}
		}
		return s;
	}
	//removes the characters present in string s1
	static String removePresentElements(int m,String s1[],String s2[],String s)
	{
		int i,j,k,flag;
		String temp="";
		for(i=0;i<s.length();i++)
		{
			flag=0;
			for(k=0;k<m;k++)
			{
				for(j=0;j<s1[k].length();j++)
				{
					if(s.charAt(i)==s1[k].charAt(j))
					{
						flag=1;
					}
				}
				for(j=0;j<s2[k].length();j++)
				{
					if(s.charAt(i)==s2[k].charAt(j))
					{
						flag=1;
					}
				}
			}
			if(flag==0)
			{
				String temo=Character.toString(s.charAt(i));
				if(temp.length()!=0)
					temp=temp+temo;
				else
				{
					temp=temo;
				}
			}
		}
		return temp;
	}
	// finds closure of a string(all the attributes that are indirectly or directly dependent on the string)
	static void findClosure(int n,int m,String s1[],String s2[],String closure[])
	{
		int i,j,p,l,r,k;
		int flagy=0;
		for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				if(flagy!=1){
					closure[i]=s1[i];
				}
				for(k=0;k<s2[i].length();k++)
				{
					if(isPresent(closure[i],s2[i].charAt(k))==0)
					{
						String temo=Character.toString(s2[i].charAt(k));
						closure[i]=closure[i]+temo;
					}
				}
			}
			flagy=1;
		}
		for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				for(j=0;j<m;j++)
				{
					if(i!=j)
					{
						if(isSubstring(s1[j],closure[i])!=-1)
						{
							for(k=0;k<s2[j].length();k++)
							{
								if(isPresent(closure[i],s2[j].charAt(k))==0)
								{
									String temo=Character.toString(s2[j].charAt(k));
									closure[i]=closure[i]+temo;
								}
							}
						}
					}
				}
			}
		}
		for(i=0;i<m;i++)
		{
			System.out.println(s1[i]+" "+closure[i]);
		}
	}
	//finds minimal cover of a string(all the attributes that are dependent only on the given string)
	static void findMinCov(int n,int m,String s1[],String closure[],char minclo[][],int len[])
	{
		int i,j,p,l,r,k;
		 for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				for(j=0;j<s1[i].length();j++)
				{
					String s4=s1[i].substring(0,j);
					if(j+1!=s1[i].length())
					{
						String s5=s1[i].substring(j+1,s1[i].length()-1);
						if(j!=0)
						s4=s4+s5;
						else
						{
							s4=s5;
						}					
					}
					for(k=0;k<m;k++)
					{
						if(isSubstring(s1[k],s4)!=-1&&k!=i)
						{
							for(l=0;l<len[i];l++)
							{
								for(r=0;r<len[k];r++)
								{
									if(minclo[i][l]==minclo[k][r])
									{
										int temp=l;
								//		cout<<i<<" "<<minclo[i][l]<<" ";
										remove(temp,minclo[i],len[i]);
										len[i]--;
										l=-1;
										break;
									//	cout<<len[i]<<" "<<s1[i]<<" "<<s1[k]<<endl;
									}
								}
							}
						}
					}
				}
			}
		}
		for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				for(k=0;k<m;k++)
				{
					if(isSubstring(s1[k],s1[i])!=-1&&k!=i)
					{
						for(l=0;l<len[i];l++)
						{
							for(r=0;r<len[k];r++)
							{
								if(minclo[i][l]==minclo[k][r])
								{
									int temp=l;
									remove(temp,minclo[i],len[i]);
									len[i]--;
									l=-1;
									break;
								}
							}
						}
					}
				}
			}
		}
		for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				for(k=0;k<m;k++)
				{
					if(isSubstring(s1[k],closure[i])!=-1&&k!=i)
					{
						for(l=0;l<len[i];l++)
						{
							for(r=0;r<len[k];r++)
							{
								if(minclo[i][l]==minclo[k][r]&&isPresent(s1[k],minclo[k][r])==0)
								{
									int temp=l;
								//	cout<<minclo[i][l]<<" "<<s1[i]<<" "<<s1[k]<<endl;
									remove(temp,minclo[i],len[i]);
									len[i]--;
									l=-1;
									break;
								}
							}
						}
					}
				}
			}
		}
		for(i=0;i<m;i++)
		{
			for(j=0;j<len[i];j++)
			{
				if(isPresent(s1[i],minclo[i][j])==0)
				{
					System.out.println(s1[i]+" "+minclo[i][j]);
				}
			}
		}
	}
	//finds smallest canidate key(removes non essential elements from the given string step by step
	static String findMinCandKey(int n,int m,String s,String s1[],String closure[],int present[])
	{
		String minCandKey=s;
		int[] present2=new int[26];
		int min=100,i,j,k,l;
		//System.out.println("hi");
		for(i=0;i<26;i++)
		{
			present2[i]=0;
		}
		for(i=0;i<minCandKey.length();i++)
		{
			String ans;
			String s4=minCandKey.substring(0,i);
			if(i+1!=minCandKey.length())
			{
				String s5=minCandKey.substring(i+1,minCandKey.length());
				if(i!=0)
				s4=s4+s5;
				else
				s4=s5;
			}
			ans=s4;
			int flag=0;
			/*for(j=0;j<100;j++)
		{
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],ans)!=-1)
				{
					for(l=0;l<closure[k].length();l++)
					{
						if(isPresent(ans,closure[k].charAt(l))==0)
						{
							String temo=Character.toString(closure[k].charAt(l));
							ans=ans+temo;
						}
					}
				}
			}
		}*/
			for(j=0;j<100;j++)
			{
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],ans)!=-1)
				{
					for(l=0;l<closure[k].length();l++)
					{
						if(isPresent(ans,closure[k].charAt(l))==0)
						{
							String temo=Character.toString(closure[k].charAt(l));
							ans=ans+temo;
						}
					}
				}
			}
			}
			for(j=0;j<ans.length();j++)
			{
				int temp4=ans.charAt(j)-'A';
				if(present2[temp4]!=1)
				present2[temp4]++;
			}
			for(j=0;j<26;j++)
			{
				if(present2[j]!=present[j])
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				String temo=minCandKey.replace(Character.toString(minCandKey.charAt(i)),"");

				minCandKey=temo;
				i=-1;			
			}
			for(j=0;j<26;j++)
			{
				present2[j]=0;
			}		
		//	System.out.println("hi");
		}
		//System.out.println("hi");
		//System.out.println(s+" here "+ minCandKey);
		return minCandKey;
	}
	//checks second normal form(an attribute is not partially dependent on the key)
	//checkSecondNormalForm(n,m,s1,minCandKey,nonPrime,s2,setOfCandKeys)
	static int checkSecondNormalForm(int n,int m,String s1[],String prime,String nonPrime,String s2[],Set<String> setOfCandKeys)
	{
		int i,k,l;
		for(i=0;i<m;i++)
		{
			if(isSubstring(s2[i],prime)==-1)
			{
				Iterator set_value = setOfCandKeys.iterator();
				int flag=0;
				while(set_value.hasNext())
				{
					String temo=set_value.next().toString();
					if(isSubstring(s1[i],temo)==0&&!s1[i].equals(temo))
					{
						flag=1;
						return 1;
					}
					
				}
			/*	if(flag==0)
				{
					return 1;
				}*/
			}
			
		}
		return 0;
	}
	//checks third normal form( non key to non key is not present)
	public static int checkThirdNormalForm(int n,int m,String s1[],String s2[],String nonPrime,String prime,Set<String> setOfCandKeys)		//checkThirdNormalForm(n,m,s1,closure,minCandKey)
	{
		int i,j;
			for(i=0;i<m;i++)
			{
				if(isSubstring(s2[i],prime)==-1)
				{
					Iterator set_value = setOfCandKeys.iterator();
					int flag=0;
					while(set_value.hasNext())
					{
						String temo=set_value.next().toString();
						if(s1[i].equals(temo))
						{
							flag=1;
						}
						
					}
					if(flag==0)
					{
						return 1;
					}
				}
			}
		
		return 0;
	}
	//checks bcnf(only from key to other attributes functional dependencies are present)
	static int checkBCNF(int n,int m,String s1[],String s2[],String nonPrime,String prime,Set<String> setOfCandKeys)
	{
		int i,j;
		for(i=0;i<m;i++)
		{
			Iterator set_value = setOfCandKeys.iterator();
			int flag=0;
			while(set_value.hasNext())
			{
				String temo=set_value.next().toString();
				if(s1[i].equals(temo))
				{
					flag=1;
				}
			}
			if(flag==0)
			{
				return 1;
			}
		}
		
		return 0;
	}
	static int presentInArray(int n1,int n2,String arr1,char arr2[])//array1 present in array2
	{
		int i,j,k;
		int flag=0;
		for(i=0;i<n1;i++)
		{
			flag=0;
			for(j=0;j<n2;j++)
			{
				if(arr1.charAt(i)==arr2[j])
				{
					flag=1;
				}
			}
			if(flag==0)
			{
				return 0;
			}
		}
		return 1;
	}
	//converts minimal cover to BCNF
	static void convertToBCNF(int n,int m,String s1[],String closure[],char minclo[][],int len[])
	{
		int i,j,k,l,p,r,temp;
		for(p=0;p<m;p++)
		{
			for(i=0;i<m;i++)
			{
				for(j=0;j<m;j++)
				{
					if(presentInArray(s1[i].length(),len[j],s1[i],minclo[j])==1)
					{
						for(k=0;k<s1[i].length();k++)
						{
							for(l=0;l<len[j];l++)
							{
								if(s1[i].charAt(k)==minclo[j][l])
								{
									temp=l;
									//System.out.println(minclo[j][l]);
									remove(temp,minclo[j],len[j]);
									len[j]--;
									l=-1;
								}
							}
						}
					}
				}
			}
		}
	}
	static String findEssentialAttributes(int n,int m,String s1[],char minclo[][],int len[])
	{
		String s="";
		int i,j,k,l,r;
		for(i=0;i<m;i++)
		{
			for(j=0;j<s1[i].length();j++)
			{
				char character=s1[i].charAt(j);
				int flag=0;
				for(k=0;k<m;k++)
				{
					//if(i)
					for(l=0;l<len[k];l++)
					{
						if(minclo[k][l]==character&&k!=i)
						{
							flag=1;
						//	System.out.println(character+" here "+minclo[k][l]+" "+k);
						}
					}
				}
				if(flag==0)
				{
					//System.out.println("hi");
					if(isPresent(s,character)==0)
					s=s+Character.toString(character);
				}
			}
		}
		return s;
	}
	static String findCommonAttributes(int n,int m,String s1[],char minclo[][],int len[])
	{
		String ret="";
		int i,j,k,l;
		for(l=0;l<m;l++)
		{
			for(i=0;i<s1[l].length();i++)
			{
				int flag=0;
				char character=s1[l].charAt(i);
				for(j=0;j<m;j++)
				{
					if(j!=l)
					{
					for(k=0;k<len[j];k++)
					{
						if(minclo[j][k]==character&&j!=l)
						{
							flag=1;
						}
					}
					}
				}
				if(flag==1)
				{
					if(isPresent(ret,character)==0)
					ret=ret+Character.toString(character);
				}
			}
		}
		return ret;
	}
	static String findUselessAttributes(int n,int m,String s1[],char minclo[][],int len[])
	{
		String ret="";
		int i,j,k,l;
		for(l=0;l<m;l++)
		{
			for(i=0;i<len[l];i++)
			{
				if(isPresent(s1[l],minclo[l][i])==0)
				{
				int flag=0;
				char character=minclo[l][i];
				for(j=0;j<m;j++)
				{
					for(k=0;k<s1[j].length();k++)
					{
						if(s1[j].charAt(k)==character&&j!=l)
						{
							flag=1;
						//	System.out.println(character+" "+j);
						}
					}
				}
				if(flag==0)
				{
					if(isPresent(ret,character)==0)
					ret=ret+Character.toString(character);
			//		System.out.println("HI");
				}
				}
			}
		}
		return ret;
	}
	static int checkPrimaryKeys2(int n,int m,String s,String closure[],int present[],String s1[])
	{
		int[] present2=new int[26];
		int min=100,i,j,k,l;
		String s4=s;
		for(i=0;i<26;i++)
		{
			present2[i]=0;
		}
			String ans;
			ans=s4;
			int flag=0;
			for(j=0;j<100;j++)
			{
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],ans)!=-1)
				{
					for(l=0;l<closure[k].length();l++)
					{
						if(isPresent(ans,closure[k].charAt(l))==0)
						{
							String temo=Character.toString(closure[k].charAt(l));
							ans=ans+temo;
						}
					}
				}
			}
			}
			for(j=0;j<ans.length();j++)
			{
				int temp4=ans.charAt(j)-'A';
				if(present2[temp4]!=1)
				present2[temp4]++;
			}
			for(j=0;j<26;j++)
			{
				if(present2[j]!=present[j])
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
			//	System.out.println(s4);	
				//vec.add(s4);
				//vec2.add(s4);
				return 1;
			}		
			return 0;
	}
	static void checkPrimaryKeys3(int n,int m,String s,String closure[],int present[],String s1[],Vector<String> cand)
	{
		int[] present2=new int[26];
		int min=100,i,j,k,l;
		String s4=s;
		for(i=0;i<26;i++)
		{
			present2[i]=0;
		}
			String ans;
			ans=s4;
			int flag=0;
			for(j=0;j<100;j++)
			{
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],ans)!=-1)
				{
					for(l=0;l<closure[k].length();l++)
					{
						if(isPresent(ans,closure[k].charAt(l))==0)
						{
							String temo=Character.toString(closure[k].charAt(l));
							ans=ans+temo;
						}
					}
				}
			}
			}
			for(j=0;j<ans.length();j++)
			{
				int temp4=ans.charAt(j)-'A';
				if(present2[temp4]!=1)
				present2[temp4]++;
			}
			for(j=0;j<26;j++)
			{
				if(present2[j]!=present[j])
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
			//	System.out.println(s4);	
				//vec.add(s4);
				//vec2.add(s4);
				cand.add(s4);
			}		
	}
	static void powerSet2(String str, String esse,int index, String curr,int n,int m,String closure[],String s1[],int present[],Vector<String> cand) 
	{  
	  
	    // base case 
	    if (index == str.length()) { 
	          checkPrimaryKeys3(n,m,curr+esse,closure,present,s1,cand);
	    } 
	  
	    // Two cases for every character 
	    // (i) We consider the character 
	    // as part of current subset 
	    // (ii) We do not consider current 
	    // character as part of current 
	    // subset 
	    if(index!=str.length())
	    powerSet2(str,esse, index + 1, curr + Character.toString(str.charAt(index)),n,m,closure,s1,present,cand); 
	if(index!=str.length())
	    powerSet2(str,esse, index + 1, curr,n,m,closure,s1,present,cand); 
	}
	//MAIN program connected to APP
	public String module1(String str,String str1,Vector<String> vec)
	{	
		App ob=new App();
		Normalization ob2 = new Normalization();
		Vector<String> vec2=new Vector();
		//EXCEPTIONS
		//if Variables field is empty
		if (str.isEmpty()) {
			//JOptionPane.showMessageDialog(shell, "Eggs are not supposed to be green.");
			MessageBox dialog = new MessageBox(App.shell, SWT.ICON_INFORMATION | SWT.OK);
			dialog.setText("Info");
			dialog.setMessage("Please fill the Variables field!");
			dialog.open();
			return null;
			//System.out.println(returnCode);
		}
		//remove/ignore all whitespaces in FD field and variables field
		str = str.replaceAll("\\s", "");	// using built in method 
		str1 = str1.replaceAll("\\s", ""); // using built in method  
	    //System.out.println(str1);
		int n=0;
		//sc.nextInt();
		int i;
		String s="";
		//String str=sc.nextLine();
		StringTokenizer stoken;
		stoken=new StringTokenizer(str,",");
		//System.out.println("Min candidate key2 is "+minCandKey2);
		char[] arr;
		arr=new char[100];
		while(stoken.hasMoreTokens())
		{
			s=s+stoken.nextToken();
		}
		n=s.length();
	//	System.out.println(n);
		int[] present;
		present=new int[26];
		for(i=0;i<26;i++)
		{
			present[i]=0;	
		}
		for(i=0;i<n;i++)
		{
			arr[i]=s.charAt(i);
			int temp=arr[i]-'A';
			present[temp]++;
		}
		int m,p;
	//	m=sc.nextInt();
		String[] s1=new String[100];
		String[] s2=new String[100];
		//str1=sc.nextLine();
		i=0;
		//System.out.println(n);
		for(i=0;i<100;i++)
		{
			s1[i]="";
			s2[i]="";
		}
		stoken=new StringTokenizer(str1,",");
		i=0;
		while(stoken.hasMoreTokens())
		{
			StringTokenizer toke;
			toke=new StringTokenizer(stoken.nextToken(),"->");
			s1[i]=s1[i]+toke.nextToken();
			s2[i]=s2[i]+toke.nextToken();
			i++;
		}
		m=i;
		int j,k;
		String remaining=removePresentElements(m,s1,s2,s);
		String[] closure=new String[m];
		System.out.println("Closure is");
		findClosure(n,m,s1,s2,closure);
		String minCandKey2=s;
		char[][] minclo=new char[m][n];
		int[] len=new int[m];
		for(i=0;i<m;i++)
		{
			for(j=0;j<closure[i].length();j++)
			{
				minclo[i][j]=closure[i].charAt(j);
			}
			len[i]=closure[i].length();
		}
		int r,flag,l;
		System.out.println("Minimum cover is");
		findMinCov(n,m,s1,closure,minclo,len);
		System.out.println("All primary keys are ");
		//void powerSet(String str, int index = 0, String curr = "",int n,int m,String closure[],String s1[],int present[]) 
		powerSet(s,0,"",n,m,closure,s1,present,vec,vec2);
		Iterator value = vec2.iterator();
		while (value.hasNext()) { 
	         //   System.out.println(value.next());
			String tempo=value.next().toString();
				if(tempo.length()<minCandKey2.length())
				{
					minCandKey2=tempo;
				}
	        }
		System.out.println("Min candidate key2 is "+minCandKey2);
		String minCandKey=findMinCandKey(n,m,s,s1,closure,present);
		minCandKey=minCandKey2;
		String essentialAttributes="",uselessAttributes="",commonAttributes="";
	//	uselessAttributes=uselessAttributes+remaining;
		
		essentialAttributes=findEssentialAttributes(n,m,s1,minclo,len);
		commonAttributes=findCommonAttributes(n,m,s1,minclo,len);
		uselessAttributes=findUselessAttributes(n,m,s1,minclo,len);
		
		essentialAttributes=essentialAttributes+remaining;
		
		System.out.println("Essential Attributes are "+essentialAttributes);
		System.out.println("Common Attributes are "+commonAttributes);
		System.out.println("Useless Attributes are "+uselessAttributes);
		
		//static int checkPrimaryKeys2(int n,int m,String s,String closure[],int present[],String s1[])
		Vector<String> cand=new Vector<String>();
		if(checkPrimaryKeys2(n,m,essentialAttributes,closure,present,s1)==1)
		{
			cand.add(essentialAttributes);
		}
		else
		{
			//static void powerSet2(String str, String esse,int index, String curr,int n,int m,String closure[],String s1[],int present[],Vector<String> cand)
			powerSet2(commonAttributes,essentialAttributes,0,"",n,m,closure,s1,present,cand);
		}
		Set<String> setOfCandKeys = new HashSet<String>();
		Iterator value2 = cand.iterator();
	//	while
		
		while (value2.hasNext()) { 
	         //   System.out.println(value.next());
			String tempo=value2.next().toString();
				System.out.println(tempo);
				String tempor=findMinCandKey(n,m,tempo,s1,closure,present);
				setOfCandKeys.add(tempor);
	        }
		Iterator set_value = setOfCandKeys.iterator();
		value2=setOfCandKeys.iterator();
		System.out.println("All candidate keys are");
		while (set_value.hasNext()) { 
	         //   System.out.println(value.next());
			String tempo=set_value.next().toString();
				System.out.println(tempo);
	        }
		
		while (value2.hasNext()) { 
	         //   System.out.println(value.next());
			String tempo=value2.next().toString();
				//System.out.println(tempo);
				for(i=0;i<tempo.length();i++)
				{
					if(isPresent(minCandKey,tempo.charAt(i))==0)
					{
						minCandKey=minCandKey+Character.toString(tempo.charAt(i));
					}
				}
	        }
	/*	value2=setOfCandKeys.iterator();
		while (value2.hasNext()) { 
			String tempo=value2.next().toString();
			Iterator value3=setOfCandKeys.iterator();
				while(value3.hasNext())
				{
					String temo=value3.next().toString();
					if(isSubstring(tempo,temo)==0&&!temo.equals(tempo))
					{
						setOfCandKeys.remove(temo);
					}
				}
	        }*/
		String nonPrime="";
		for(i=0;i<s.length();i++)
		{
			if(isPresent(minCandKey,s.charAt(i))==0)
			{
				nonPrime=nonPrime+Character.toString(s.charAt(i));
			}
		}
		System.out.println("After concatenation "+minCandKey);
		System.out.println("Non Prime attributes are "+ nonPrime);
		//candKey=candKey+minCandKey;
		//System.out.println("Minimum candidate key is "+minCandKey);
	//	System.out.println("hi");
		String s6=s;
	//	System.out.println("hi");
		for(i=0;i<s6.length();i++)
		{
			if(isPresent(minCandKey,s6.charAt(i))==1)
			{
				String temo=s6.replace(Character.toString(s6.charAt(i)),"");
				s6=temo;
				i=-1;
			}
		//	System.out.println("hi 634");
		}
		int flag3=1,flag4=1;
		//System.out.println("hi 638");
		flag=checkSecondNormalForm(n,m,s1,minCandKey,nonPrime,s2,setOfCandKeys);//int checkSecondNormalForm(int n,int m,string s1[],string s6,string minCandKey,string closure[])
		if(flag==1)
		{
			System.out.println("Normal form is 1NF");
			ob.val="1NF";
			App.btnNormalize.setEnabled(true);
		}
		else
		{
			//checkThirdNormalForm(int n,int m,String s1[],String s2[],String nonPrime,String prime,Set<Iterator> setOfCandKeys)
			int flag2=checkThirdNormalForm(n,m,s1,s2,nonPrime,minCandKey,setOfCandKeys);
			flag3=flag2;
			if(flag2==1)
			{
				System.out.println("Normal form is 2NF");
				ob.val="2NF";
				App.btnNormalize.setEnabled(true);
			}
			else
			{
				int flag5=checkBCNF(n,m,s1,s2,nonPrime,minCandKey,setOfCandKeys);
				flag4=flag5;
				if(flag5==1)
				{
					System.out.println("Normal form is 3NF");
					//findMinCov(n,m,s1,closure,minclo,len);
					convertToBCNF(n,m,s1,closure,minclo,len);
					ob.val="3NF";
					App.btnNormalize.setEnabled(true);
				}
				else
				{
					System.out.println("Normal form is BCNF");
					ob.val="BCNF";
					App.btnNormalize.setEnabled(false);
				}
			}
		}
		if(flag4==1)
		{
			System.out.println("After Normalization");
			k=0;
			for(i=0;i<m;i++)
			{
				if(len[i]!=0)
				{
					System.out.println("Relation "+(k+1));
					relations += ("\n" + "Relation "+(k+1) + "\n");
//					ob2.relations_textArea.setText("Hello World!");
//					ob2.relations_textArea.append("Relation "+ (k+1) + "\n");
//					ob2.relations_textArea.append(s1[i]+" ");
					String con=s1[i];
					for(j=0;j<len[i];j++)
					{
						if(isPresent(s1[i],minclo[i][j])==0)
						{
							String temo=Character.toString(minclo[i][j]);
							con=con+temo;
						}
					}
					System.out.print("Attributes are ");
					relations += ("Attributes are: ");
					char[] chars=con.toCharArray();
					Arrays.sort(chars);
					System.out.print(chars[0]);
					relations += (chars[0]);
					for(j=1;j<con.length();j++)
					{
						System.out.print(","+chars[j]);
						relations += (","+chars[j]);
					}
					int conto=1;
					for(j=0;j<len[i];j++)
					{
						if(isPresent(s1[i],minclo[i][j])==0)
						{
							conto=0;
						}
					}
					if(conto==0)
					{
					System.out.println("\n"+"Functional Dependencies are");
					relations += ("\n" +"Functional Dependencies are\n");
					System.out.print(s1[i]+" ");
					relations += (s1[i]+" ");
					for(j=0;j<len[i];j++)
					{
						if(isPresent(s1[i],minclo[i][j])==0)
						{
							System.out.print(minclo[i][j]);
							relations += (minclo[i][j]);
//							ob2.relations_textArea.append(Character.toString(minclo[i][j]));
						}
					}
					}
					else
					{
						System.out.println("\n"+"Functional Dependencies are none");
						relations += ("\n" +"Functional Dependencies are none ");
					}
					String temo=filter(s,s1[i],minclo[i],len[i]);
					System.out.println("\n"+"Super Keys are ");
					relations += "\n"+"Super Keys are " + "\n";
//					ob2.relations_textArea.append("\n"+"Primary Keys are " + "\n");
					powerSet(temo,0,"",s1[i]);
					k++;
				}
			}
			if(remaining!="")
			{
				System.out.println("Relation "+(k+1));
				relations += ("\n" + "Relation "+(k+1) + "\n");
				System.out.print("Attributes are ");
				relations += ("Attributes are: ");
				System.out.println(remaining.charAt(0));
				relations+=(remaining.charAt(0));
				for(i=1;i<remaining.length();i++)
				{
					System.out.println(","+remaining.charAt(i));
					relations+=(","+remaining.charAt(i));
				}
				System.out.println("\nFunctional Dependencies are none\n");
				relations+=("\nFunctional Dependencies are none\n");
				System.out.println("\n Super Keys are \n "+ remaining);
				relations+=("\n Super keys are \n "+ remaining);
			}
			
		}
		return minCandKey2;
	}
}
/*

A,B,C,D,E,F
A->B,C->DE,AC->F


6
ABCDEF
3
A B
C DE
AC F


6
ABCDEF
2
AC BDEF
D B

6
ABCDEF
2
AC BDEF
D C

6
ABCDEF
1
AC BDEF

6
ABCDEF
5
A B
B C
C D
D E
E F

6
ABCDEF
5
B A
C B
D C
E D
F E

3
ABD
3
B A
D A
AB D

4
ABCD
3
B A
D A
AB D

A,B,C,D,E
A->BCD,BC->AD,D->B

*/
