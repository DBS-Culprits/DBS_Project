import java.util.*;
public class Module1
{
	StringBuffer sb = new StringBuffer(40);
	 static String swap(String str, int i, int j) 
    { 
        StringBuilder sb = new StringBuilder(str); 
        sb.setCharAt(i, str.charAt(j)); 
        sb.setCharAt(j, str.charAt(i)); 
        return sb.toString(); 
    }
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
	static void remove(int l,char minclo[],int len)
	{
		int j,k;
		for(j=l;j<len-1;j++)
		{
			minclo[j]=minclo[j+1];
		}
	}
	static void checkPrimaryKeys(int n,int m,String s,String closure[],int present[],String s1[])
	{
		int[] present2=new int[26];
		int min=100,i,j,k,l;
		String s4=s;
		//System.out.println("hi");
		for(i=0;i<26;i++)
		{
			present2[i]=0;
		}
			String ans;
			ans=s4;
			int flag=0;
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],s4)!=-1)
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
			}		
	}
	//checkPrimaryKeys(n,m,a,closure,present,s1); 
        static void powerSet(String str, int index, String curr,int n,int m,String closure[],String s1[],int present[]) 
		{  
		  
		    // base case 
		    if (index == str.length()) { 
		          checkPrimaryKeys(n,m,curr,closure,present,s1);
		    } 
		  
		    // Two cases for every character 
		    // (i) We consider the character 
		    // as part of current subset 
		    // (ii) We do not consider current 
		    // character as part of current 
		    // subset 
		    if(index!=str.length())
		    powerSet(str, index + 1, curr + Character.toString(str.charAt(index)),n,m,closure,s1,present); 
		if(index!=str.length())
		    powerSet(str, index + 1, curr,n,m,closure,s1,present); 
		}
		static void powerSet(String str, int index, String curr , String s2) 
		{ 
		    int n = str.length(); 
		  
		    // base case 
		    if (index == n) { 
		         
		        if(isSubstring(s2,curr)==0)
		        {
		        	System.out.println(curr);
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
	//findMinCandKey(s,minCandKey,s1,closure,present);
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
			for(k=0;k<m;k++)
			{
				if(isSubstring(s1[k],s4)!=-1)
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
		return minCandKey;
	}
	static int checkSecondNormalForm(int n,int m,String s1[],String s6,String minCandKey,String closure[])
	{
		int i,k,l;
		n=minCandKey.length();
			for(i=0;i<minCandKey.length();i++)
			{
				String s4="";
				   s4=minCandKey.substring(0,i);
				if(i+1!=n){
					String s5=minCandKey.substring(i+1,n-1);
					if(i!=0)
						s4=s4+s5;
					else
					{
						s4=s5;
					}
				}
				for(k=0;k<m;k++)
				{
					if(isSubstring(s1[k],s4)!=-1)
					{
						for(l=0;l<s6.length();l++)
						{
							if(isPresent(closure[k],s6.charAt(l))==1)//found a partial dependency
							{
								return 1;				// separate
															//store the functional dependencies of s1[k] in ans[l][];
															//check if s6[l] is not dependent on some other attribute
															//erase all attributes of s6[
							}
						}
					}
				}
			}
			return 0;
	}
	public static int checkThirdNormalForm(int n,int m,String s1[],String closure[],String minCandKey)		//checkThirdNormalForm(n,m,s1,closure,minCandKey)
	{
		int i,j;
			for(i=0;i<m;i++)
			{
				for(j=0;j<closure[i].length();j++)
				{
					if(isPresent(s1[i],closure[i].charAt(j))==0)
					{
						if(!s1[i].equals(minCandKey)&&isPresent(minCandKey,closure[i].charAt(j))==0)
						{
							return 1;
						}
					}
				}
			}
		return 0;
	}
	//checkBCNF(n,m,s1,minCandKey);
	static int checkBCNF(int n,int m,String s1[],String minCandKey)
	{
		int i,j;
		    for(i=0;i<m;i++)
				{
					if(!s1[i].equals(minCandKey))
					{
						return 1;
					}
				}
				return 0;
	}
	//convertToBCNF(n,m,s1,closure,minclo,len);
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
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int i;
		String s=sc.next();
		char[] arr;
		arr=new char[100];
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
		m=sc.nextInt();
		String[] s1=new String[m];
		String[] s2=new String[m];
		for(i=0;i<m;i++)
		{
			s1[i]=sc.next();
			s2[i]=sc.next();
		}
		int j,k;
		String remaining=removePresentElements(m,s1,s2,s);
		String[] closure=new String[m];
		System.out.println("Closure is");
		findClosure(n,m,s1,s2,closure);
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
		powerSet(s,0,"",n,m,closure,s1,present);
		String minCandKey=findMinCandKey(n,m,s,s1,closure,present);
		System.out.println("Minimum candidate key is "+minCandKey);
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
		flag=checkSecondNormalForm(n,m,s1,s6,minCandKey,closure);//int checkSecondNormalForm(int n,int m,string s1[],string s6,string minCandKey,string closure[])
		if(flag==1)
		{
			System.out.println("Normal form is 1NF");
		}
		else
		{
			int flag2=checkThirdNormalForm(n,m,s1,closure,minCandKey);
			flag3=flag2;
			if(flag2==1)
			{
				System.out.println("Normal form is 2NF");
			}
			else
			{
				int flag5=checkBCNF(n,m,s1,minCandKey);
				flag4=flag5;
				if(flag5==1)
				{
					System.out.println("Normal form is 3NF");
					//findMinCov(n,m,s1,closure,minclo,len);
					convertToBCNF(n,m,s1,closure,minclo,len);
				}
				else
				{
					System.out.println("Normal form is BCNF");
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
					System.out.print(s1[i]+" ");
					for(j=0;j<len[i];j++)
					{
						if(isPresent(s1[i],minclo[i][j])==0)
						{
							System.out.print(minclo[i][j]);
						}
					}
					String temo=filter(s,s1[i],minclo[i],len[i]);
					System.out.println("\n"+"Primary Keys are ");
					if(k==0)
					{
						temo=temo+remaining;
						s1[i]=s1[i]+remaining;
					}
					powerSet(temo,0,"",s1[i]);
					k++;
				}
			}
		}
	}
}
/*
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

*/