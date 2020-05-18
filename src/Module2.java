import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
/*
   variable names : bname --> bucket name
                    ld --> local depth
                    gd --> global depth
                    m --> hash number (i.e key mod m)
                    key --> number to be operated
                    bucketIndex -->index of the respective bucket
                    bf --> blocking factor
                    bucketHashNum --> string in bucket name (for eg. B001 then 001 is bucketHashNum)
*/
class Bucket
{
	
    protected String bname; 
    private int ld;  
    private StringBuilder bucketHashNum;
    private int bf;
    private int[] keys;
    private int bucketIndex; 
    protected Bucket(int ld, String bucketHashNum, int bf, boolean bit) // method to construct a bucket
    {
        this.ld = ld;
        this.bucketHashNum = new StringBuilder(bucketHashNum);
        if(bit)
        {
            this.bucketHashNum.insert(0, 1);
        }
        else
        {
            this.bucketHashNum.insert(0, 0);
        }
        this.bucketIndex = -1;
        this.bf = bf;
        this.keys = new int[bf];
        this.bname = "B" + this.bucketHashNum.toString();
    }
    

   protected boolean checkInsert()    // method to check if a number can be inserted in bucket
    {    
        // logic--> if the current index of that bucket is less than bf then number can be inserted
      boolean h= ((this.bucketIndex + 1) < this.bf);
        return h ;
    }

   
    protected boolean checkEmpty()   // function to check if bucket is empty
    {
        // logic- obvious
        boolean y=this.bucketIndex < 0;
        return y ;
    }

    protected void insertKey(int key) // function to insert keys in the bucket list
    {
        try 
        {
            // logic- increment the bucket index then add the input key into keys array
            ++this.bucketIndex; 
            this.keys[this.bucketIndex] = key;
        } 
        catch(ArrayIndexOutOfBoundsException e) // exception if bucketIndex is greater than bf-1;
        {
            --this.bucketIndex;
            System.out.println("Either change the blocking factor or use correct sequence of keys");
            //insert_display += "Either change the blocking factor or use correct sequence of keys";
        }
    }
    protected Bucket bucketSplit(){
        ++this.ld;
        Bucket newBucket = new Bucket(this.ld, this.bucketHashNum.toString(), this.bf, true);
        this.bucketHashNum.insert(0, 0);
        this.changebname();
        // Distribue Keys
        //this.distributeKeys(newBucket, 1);
        // this.distributeKeys(newBucket, 1);
        return newBucket;
    }
    protected Bucket bucketSplit(int hashSolver) // function for splitting a bucket
    {
        // logic --> increment the local depth of that bucket
        //           create a new bucket with name B1x where x is old bucketHashNum
        //           old bucket name changed to B0x     where x is old bucketHashNum
        //           rearrange keys in these two buckets(old bucket and new bucket) by calling rearrangeKeys function
        ++this.ld;
        Bucket newBucket = new Bucket(this.ld, this.bucketHashNum.toString(), this.bf, true);
        this.bucketHashNum.insert(0, 0);
        this.changebname();
        this.rearrangeKeys(newBucket, hashSolver);
        return newBucket;
    }

    private void rearrangeKeys(Bucket newBucket, int hashSolver) // function to rearrange keys during bucket split
    {
        /* logic -->  create a vector old values
                      adding all values of keys array of old bucket into vector old values
                      iterating over values in vector and find the hashed key and convert it to binary(to its ld)
                      if this keyhash is not equal to old bucket hash num then insert it to new bucket
        */
        ArrayList<Integer> oldValues = new ArrayList<Integer>(); 
        for(int k : this.keys) 
        {
            oldValues.add(k);
        }
        Iterator<Integer> oldValuesIt = oldValues.iterator();
        while(oldValuesIt.hasNext())
        {
            int key = oldValuesIt.next();
            int hashedKey = key % hashSolver;
          //  System.out.println(hashedKey);

            String keyHash = Convert.trim(Convert.binaryConversion(hashedKey), this.ld);
           // System.out.println(keyHash);

            if(!this.bucketHashNum.toString().equals(keyHash))
            {
                
                if(newBucket.checkInsert())
                {
                    newBucket.insertKey(key);
                    oldValuesIt.remove();
                    --this.bucketIndex;
                }
            }
        }
        Arrays.fill(this.keys, 0); // intialising all values of keys array(old bucket) to zero
        int i=0;
        while(i!=oldValues.size()) // adding remaining all values to keys array
        {
            this.keys[i] = oldValues.get(i);
            i++;
        }
    }

    protected boolean keyChecker(int key) // to check if  a key is present or not
    {
        //logic--> obvious
        int i=0;
        while(i!=bf)
        {
            if(this.keys[i] == key)
            {
                return true;
            }
            i++;
        }
        return false;
    }

    protected void deleteKey(int key) // function to delete a key
    {
        //logic- pretty obvious, first find the key to be deleted then from there replace each index key with index+1 key
        System.out.println("        Deleted " + key);
        //delete_display += ("        Deleted " + key + "\n");
        int index = -1;
        int i=0;
        while(i!=this.bf)
        {
            if(this.keys[i] == key)
            {
                index = i;
                break;
            }
            i++;
        }
         i=index;
        while(i!=(this.bf-1))
        {
            this.keys[i] = this.keys[i+1];
            ++index;
            i++;
        }
         i=index;
        while(i!=this.bf)
        {
            this.keys[i] = 0;
            i++;
        }
        --this.bucketIndex;
}
    protected boolean checkMerge(Bucket mergeTo) // function to check merge
    {
        //logic --> merge is possible if local depth of that bucket is greater than 1 AND bucket is less than half filled
        boolean f=(mergeTo.findLD() > 1);
        boolean g=(mergeTo.getBucketIndex()/this.bf <= 0.5);
        return f && g;
    }

    protected void merge() // function to merge buckets
    {
        --this.ld;
        this.bucketHashNum.deleteCharAt(0);
        this.changebname();
    }

    private void changebname() // function to change name of bucket
    {
        this.bname = "B" + this.bucketHashNum.toString();
    }

    public String toString() // function to display output  (see output format to understand code)
    {
        StringBuilder str = new StringBuilder();
        str.append(this.bname + ": ");
        str.append(Arrays.toString(this.keys));
        return str.toString();
    }

    public boolean equals(Object O) // for checking equality(logic- bucketHashNum should be equal)
    {
        Bucket b = (Bucket)O;
        boolean ans=this.bucketHashNum.toString().equals(b.getBucketHashNum());
        return ans ;
    }

    protected int findLD() // function to find local depth of bucket
    {
        return this.ld;
    }
    protected String getBucketHashNum()  // function to find hash number of that bucket for eg. 001 or 10 etc
    {
        return this.bucketHashNum.toString();
    }
    protected int getBF() // function to find blocking factor of that bucket
    {
        return this.bf;
    }
    protected int[] getKeys() // function to get keys of respective bucket
    {
        return this.keys;
    }
    protected int getBucketIndex()  // function to get bucket index(current index value) of bucket
    {
        return this.bucketIndex;
    }

}
class BucketData  //this class serves the purpose of storing  all buckets

{
    int bf;
    ArrayList<Bucket> dataBuckets;  // vector of buckets
    protected BucketData(int bf, int ld) // function to add buckets to bucket vector
    {
      /*
        logic--> two create two buckets B0 and B1;
                add to the bucket vector
                split buckets according to local depth and add to bucket vector
     */

        int count = ld-1; 
        this.bf = bf;
        this.dataBuckets = new ArrayList<Bucket>(); 
        Bucket b0 = new Bucket(1,"", bf, false);
        Bucket b1 = new Bucket(1,"", bf, true);
        this.dataBuckets.add(b0);  
        this.dataBuckets.add(b1);
        int i=0;
        while(i!=count)
        {
            int length = this.dataBuckets.size();
             int j=0;
            while(j!=length)
            {
                Bucket b = this.dataBuckets.get(j).bucketSplit();
                this.dataBuckets.add(b);
                j++;
            }
            i++;

        }
        this.sortBuckets(this.dataBuckets); // sorting the buckets by using sortBuckets function
    }


    protected void addBucket(Bucket newBucket) // adding the new bucket after a split to the Bucket vector
    {
        // logic --> simply add and then sort
        this.dataBuckets.add(newBucket);
        this.sortBuckets(this.dataBuckets);
    }

    protected void mergeBuckets(Bucket emptyBucket, Bucket mergeToBucket) //merging the buckets in case of an empty bucket
    {
        // logic --> merge using merge function and then remove the empty bucket and sort again
        mergeToBucket.merge();
        this.dataBuckets.remove(emptyBucket);
        this.sortBuckets(this.dataBuckets);
    }

    protected Bucket getBucket(String bitString) // function to find a bucket
    {
        //logic--> find its trimmed value(explained in Convert class) and loop over the bucket vector
        for(Bucket b : this.dataBuckets)
        {
            String trimmedVal = Convert.trim(bitString, b.findLD());
            if(trimmedVal.equals(b.getBucketHashNum()))
            {
                return b;
            }
        }
        return null; // incase bucket not found
    }

    protected Bucket getSplitBucket(Bucket bucket) // function to find split bucket
    {
        String bname = bucket.getBucketHashNum();
        char bit = bname.charAt(0);
        StringBuilder str = new StringBuilder(bname.substring(1));
        if(bit == '0')
        {
            str.insert(0, 1);
        } 
        else if(bit == '1')
        {
            str.insert(0, 0);
        }
        else 
        {
            return null;
        }
        for(Bucket b : this.dataBuckets)
        {
            if(b.getBucketHashNum().equals(str.toString()))
            {
                return b;
            }
        }
        return null;
    }
    protected int getMaxLocalDepth(){
        int ld = this.dataBuckets.get(0).findLD();
        for(int i=1; i<this.dataBuckets.size(); i++){
            if(ld < this.dataBuckets.get(i).findLD()){
                ld = this.dataBuckets.get(i).findLD();
            }
        }
        return ld;
    }
    protected static void sortBuckets(ArrayList<Bucket> buckets){
        Collections.sort(buckets, new SortBitString());
    }

    protected int bucketCount()  // function to get count of buckets
    {
        return this.dataBuckets.size();
    }

    public String toString()// to display output(see output format to understand code)
    {
        StringBuilder str = new StringBuilder();
        for(Bucket b : this.dataBuckets)
        {
            str.append("|  ");
         
            str.append(b.bname + "   ");
            int[] keys = b.getKeys();
             int i=0;
            while(i!=b.getBF())
            {
                str.append(" " + keys[i] + " ");
                i++;
            }
            str.append("  \n");
        }
        return str.toString();
    }

}
class SortBitString implements Comparator<Bucket>{
    public int compare(Bucket a, Bucket b){
        return a.getBucketHashNum().compareTo(b.getBucketHashNum());
    }
} 
 class BucketList
 {
	//String insert_display = "";
	//String search_display = "";
	String delete_display = "";
		
    private int gd;
    private int modFunc; // value is equal to 10 for this simulator
    private BucketData bucketData;
    private ArrayList<ListRecord> bucketList;

    private String currentBucket, hashvalue;
    
    public BucketList(int gd, int ld, int bf,int modFunc)// function to generate bucket list with initial parameters
    {
        this.gd = gd;


        this.modFunc = modFunc;
        this.bucketData = new BucketData(bf, ld);
        this.bucketList = new ArrayList<ListRecord>();
        

        this.generateBucketList();
        System.out.println(Math.pow(2, gd) + " " + this.bucketData.bucketCount());
    }

    public void insertKey(int key) //function to insert
    {
      /*  int h = key % modFunc; // by taking mod
        ListRecord r = this.getListRecord(h);
        Bucket b = r.getBucket();
        this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd); // convert h into trimmed binary(check convert class for better understanding)
        this.currentBucket = b.toString();
      
// check insert, if possible then insert else check if local depth is less than global depth or not,
        // if ld is >=gd then increase gd and then split bucket
        if(b.checkInsert())
        {
            b.insertKey(key);
        } 
        else
         {
            if(b.findLD() >= gd)
            {
                ++this.gd;
                this.generateBucketList();
            }
            Bucket newBucket = b.bucketSplit(modFunc);
            if(b.getBucketHashNum().equals(Convert.trim(Convert.binaryConversion(h), b.findLD())))
            {
           */
           if(!this.searchKey(key)){
            int h = key % modFunc;
            // System.out.println(key + " % " + modGrouper + " = Hashed key " + h + ": ");
            ListRecord r = this.getListRecord(h);
            Bucket b = r.getBucket();
            this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd);
            this.currentBucket = b.toString();
            // System.out.println(" Bucket is: " + b);
            if(b.checkInsert()){
                // System.out.println(" Can Insert Key.");
                // System.out.println("My Current Index is: " + b.getCurrentIndex());

                b.insertKey(key);
            } 
            else
            {
                if(b.findLD() >= gd){
                    ++this.gd;
                    this.generateBucketList();
                }
                Bucket newBucket = b.bucketSplit(modFunc);
                // System.out.println(" Old Bucket: " + b);
                // System.out.println(" New Bucket: " + newBucket);
                if(b.getBucketHashNum().equals(Convert.trim(Convert.binaryConversion(h), b.findLD()))){
                    b.insertKey(key);
                } else {
                    newBucket.insertKey(key);
                }
                // System.out.println(" Old Bucket: " + b);
                // System.out.println(" New Bucket: " + newBucket);
                this.bucketData.addBucket(newBucket);
                this.generateBucketList();
            }
                //newBucket.insertKey(key);
              


        }
            else {
            System.out.println("Key is already present!!");
        }
            //this.bucketData.addBucket(newBucket);
            //this.generateBucketList();
    }
    

    public boolean searchKey(int key)// for searching a key
    {
        int h = key % modFunc;
        ListRecord r = this.getListRecord(h);
        Bucket b = r.getBucket();
        this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd);
        this.currentBucket = b.toString();
        if(b.keyChecker(key))
        {
            return true;
        }
        return false;
    }

    public void deleteKey(int key) // function to delete a key
    {
       // logic--> check for key and delete it, incase of an empty bucket condition merge the buckets
        int h = key % modFunc; 
        ListRecord r = this.getListRecord(h);
        Bucket b = r.getBucket();
        this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd);
        this.currentBucket = b.toString();
        System.out.println("Bucket is: " + b);
        delete_display = ("    Bucket is: " + b + "\n");
        if(b.keyChecker(key))
        {
            System.out.println("        Key " + key + " is present in " + b);
            delete_display += ("        Key " + key + " is present in " + b + "\n");
            b.deleteKey(key);
            System.out.println("        After deleting: " + b);
            delete_display += ("        After deleting: " + b + "\n");
            if(b.checkEmpty())
            {
                System.out.println("        " + b + " is empty.");
                delete_display += ("        " + b + " is empty." + "\n");
                Bucket splitBucket = this.bucketData.getSplitBucket(b);
                System.out.println("        " + splitBucket + " is the split bucket.");
                delete_display += ("        " + splitBucket + " is the split bucket." + "\n");
                if((splitBucket != null) && (b.checkMerge(splitBucket)))
                {
                    this.bucketData.mergeBuckets(b, splitBucket);
                    this.generateBucketList();
                }
            }
        }
    }

    private ListRecord getListRecord(int key)
    {
        String bitString = Convert.trim(Convert.binaryConversion(key), this.gd);
        for(ListRecord r : this.bucketList)
        {
            if(bitString.equals(r.getGlobalHashValue()))
            {
                return r;
            }
        }
        return null;
    }

    private void generateBucketList()
    {
        this.bucketList.clear();
        for(int i=0; i<Math.pow(2, this.gd); i++)
        {
            String bitString = Convert.binaryConversion(i);
            this.bucketList.add(new ListRecord(Convert.trim(bitString, gd), bucketData.getBucket(bitString)));//VALUES
        }
    }
    public String toString() // buckets toString(see output format to understand)
    {
        StringBuilder str = new StringBuilder("\n");
        for(ListRecord r : this.bucketList)
        {
            str.append(r.toString() + "   \n");
        }
        str.append(" ");
        return str.toString();
    }
    public String getCurrentBucket(){
        return this.currentBucket;
    }

    public String getHashValue(){
        return this.hashvalue;
    }

}
class ListRecord // this class is for  global hashvalue
{
    StringBuilder globalHashValue;
    Bucket bucket;
    
    protected ListRecord(boolean bit, Bucket bucket)
    {
        this.globalHashValue = new StringBuilder();
        if(bit)
        {
            this.globalHashValue.insert(0, 1);
        } 
        else 
        {
            this.globalHashValue.insert(0, 0);
        }
        this.bucket = bucket;
    }

    protected ListRecord(String bitString, Bucket bucket)
    {
        this.globalHashValue = new StringBuilder(bitString);
        this.bucket = bucket;
    }

    protected ListRecord(String bitString, boolean bit, Bucket bucket)
    {
        this.globalHashValue = new StringBuilder(bitString);
        if(bit)
        {
            this.globalHashValue.insert(0, 1);
        } 
        else
        {
            this.globalHashValue.insert(0, 0);
        }
        this.bucket = bucket;
    }

    public boolean equals(Object O) // for checking equality
    {
        ListRecord dR = (ListRecord)O;
        boolean ans=this.globalHashValue.toString().equals(dR.getGlobalHashValue());
        return ans;
    }

    public String toString() // function to display output(see output format to understand code)
    {
        StringBuilder s = new StringBuilder();
        s.append("  ");
        s.append(this.globalHashValue.toString());
        s.append("  |  ");
        s.append(this.bucket.toString());
        return s.toString(); 
    }

    protected String getGlobalHashValue(){ // function to get global hash value i.e 00 or 01 or 10 etc
        return this.globalHashValue.toString();
    }
    protected Bucket getBucket(){ // function to get bucket
        return this.bucket;
    }
}
class Convert // class for basic Utils
{
    protected static String binaryConversion(int n) // function to convert decimal into binary
    {
        // logic --> Obvious
      // Assumption --> 10 bit string

        int num = n;
        StringBuilder bitString = new StringBuilder();
        for(int i=0; i<10; i++)
        {
            bitString.insert(0, num % 2);
            num = num / 2;
        }
        return bitString.toString();
    }

    protected static String trim(String bitString, int depth)  // function to trim the string according to its local depth
                                                            // for eg 2 binary conversion is 0000000010 is trimmed to 01
    {
        // logic --> obvious
        if(bitString.length() > depth)
        {
            return bitString.substring((bitString.length() - depth), bitString.length());
        }
        else if (bitString.length() == depth){
            return bitString;
        }
        return null;
    }
    /*

    protected static void sortBuckets(ArrayList<Bucket> buckets) //function to sort the buckets after splitting and doing other operations
    {
        Collections.sort(buckets, new SortBitString());
    }
}

class SortBitString implements Comparator<Bucket> // class for sorting the buckets
{
    public int compare(Bucket a, Bucket b)
    {
        return a.getBucketHashNum().compareTo(b.getBucketHashNum());//for sorting according to the value 00,01
    }
    */
}
class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("                                ***EXTENDIBLE HASHING SIMULATOR***                                    ");
        System.out.println("    ");
        System.out.println(" * Initial Parameters input format : bf m gd ld    ");
        System.out.println("                                     key operation ");
        System.out.println("   where bf-blocking factor, m-hash value, gd-global depth, ld- local depth");
        System.out.println(" * Hash function is key mod 10");
        System.out.println(" * Operations are Insert - I, Delete - D, Search - S");
        System.out.println(" * Type 'exit' to exit the program ");

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int bf,gd, ld,m, key;  
        BucketList bucketList = null;  // creating a null bucketlist(bucketlist is a function)

        System.out.print("> Input Initial Parameters: ");
        String str = br.readLine(); // input of initial parameters stored in string str 
        StringTokenizer stoken = new StringTokenizer(str, " "); // for reading each parameter
       
       /*
            Below code is to store parameter values in their respective variables
       */                                                         
        while(stoken.hasMoreTokens())
        {
            bf = Integer.parseInt(stoken.nextToken());
            m = Integer.parseInt(stoken.nextToken());
           
            gd = Integer.parseInt(stoken.nextToken());
           
            ld = Integer.parseInt(stoken.nextToken()); // reading total of 4 inputs
            bucketList = new BucketList(gd, ld, bf,m);  // constructing bucketlist(buckets) from input parameters
            System.out.println(bucketList);    // printing the buckets with all values zero initially
        }
  do{                   
            str = br.readLine();  // reading the input for operations. Input format key operation.
            if(!str.equals("exit"))
            {
                if(str != null)
                 {   
                        stoken = new StringTokenizer(str, " ");
                        if(stoken.countTokens() < 2) // condition if only one input is given
                        {
                            System.out.println("Please input key and operation both(check input format)");
                        } 
                        else 
                        {
                            key = Integer.parseInt(stoken.nextToken()); // storing key value in its variable
                            switch(stoken.nextToken())   // cases for the operation
                            {
                                case "I":  // Insert Case
                                 {
                                    System.out.println("Inserted " + key + ": ");
                                    //insert_display += ("Inserted " + key + ": " + "\n");
                                    bucketList.insertKey(key);
                                    break;
                                 }
                                case "D":  // Delete case
                                {
                                    System.out.println("Deleted " + key + ": ");
                                    //delete_display += ("Deleted " + key + ": " + "\n");
                                    bucketList.deleteKey(key);
                                    break;
                                }
                                case "S":  // Search Case
                                {
                                    System.out.println("Searching key " + key + ": ");
                                    if(bucketList.searchKey(key))
                                    {
                                        System.out.println("Present");
                                      //search_display += ("Present" + "\n");
                                    } 
                                    else
                                    {
                                        System.out.println("Absent");
                                      //search_display += ("Absent" + "\n");
                                    }
                                    break;
                                }
                                
                                default : // case when input is different from I,S,D.
                                {
                                    System.out.println("Not a valid operation");
                                    break;
                                }
                            }
                       }
               }
            }
       System.out.println(bucketList); // Printing buckets after each operations
        } while(!str.equals("exit"));

        System.out.println(bucketList); // Printing the buckets after exit
        System.out.println("After the all Keys are done:\n" + bucketList);
    }
}
        