import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

class Bucket
{
    protected String bname; // bucket name
    private int ld;
    private StringBuilder bucketHashNum;
    private int bf;
    private int[] keys;
    private int bucketIndex; // index inside a bucket


    protected Bucket(int ld, int bf, boolean bit)
    {
        this.ld = ld;
        this.bucketHashNum = new StringBuilder();
        if(bit) 
        {
            this.bucketHashNum.insert(0, 1);  // index 0 value 1;
        } 
        else
        {
            this.bucketHashNum.insert(0, 0); // index 0 value 0;
        }
        this.bf = bf;
        this.keys = new int[bf];
        this.bucketIndex = -1;
        this.bname = "B" + this.bucketHashNum.toString();
    }

   
    protected Bucket(int ld, String bucketHashNum, int bf, boolean bit)
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
    

   protected boolean checkInsert(){ 
      boolean h= ((this.bucketIndex + 1) < this.bf);
        return h ;
    }

    // function to check if bucket is empty
    protected boolean checkEmpty()
    {
        boolean y=this.bucketIndex < 0;
        return y ;
    }

    protected void insertKey(int key) // function to insert keys in the bucket list
    {
        try 
        {
            ++this.bucketIndex; 
            this.keys[this.bucketIndex] = key;
        } 
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Either change the blocking factor or use correct sequence of keys");
        }
    }

    protected Bucket bucketSplit() 
    {
        ++this.ld;
        Bucket newBucket = new Bucket(this.ld, this.bucketHashNum.toString(), this.bf, true); 
        this.bucketHashNum.insert(0, 0);
        this.changebname();
        this.rearrangeKeys(newBucket, 1);
        return newBucket;
    }
    
    protected Bucket bucketSplit(int hashSolver)
    {
        ++this.ld;
        Bucket newBucket = new Bucket(this.ld, this.bucketHashNum.toString(), this.bf, true);
        this.bucketHashNum.insert(0, 0);
        this.changebname();
        this.rearrangeKeys(newBucket, hashSolver);
        return newBucket;
    }

    private void rearrangeKeys(Bucket newBucket, int hashSolver)
    {
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
            String keyHash = Convert.trim(Convert.binaryConversion(hashedKey), this.ld);
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
        Arrays.fill(this.keys, 0);
        int i=0;
        while(i!=oldValues.size())
        {
            this.keys[i] = oldValues.get(i);
            i++;
        }
    }

    protected boolean keyChecker(int key)
    {
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

    protected void deleteKey(int key)
    {
        System.out.println("        Deleted " + key);
        int index = -1;
        int i=0;
        while(i!=this.bf)
        {
            if(this.keys[i] == key)
            {
                index = i;
                 i++;
                break;
            }
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
    protected boolean canMergeWith(Bucket mergeTo)
    {
        boolean f=(mergeTo.findLD() > 1);
        boolean g=(mergeTo.getBucketIndex()/this.bf <= 0.5);
        return f && g;
    }

    protected void merge()
    {
        --this.ld;
        this.bucketHashNum.deleteCharAt(0);
        this.changebname();
    }

    private void changebname()
    {
        this.bname = "B" + this.bucketHashNum.toString();
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        int length = 11 - this.bname.length();
        int i=0;
        while(i!=length)
        {
            str.append(" ");
            i++;
        }
        str.append(this.bname + ": ");
        str.append(Arrays.toString(this.keys));
        return str.toString();
    }

    public boolean equals(Object O)
    {
        Bucket b = (Bucket)O;
        boolean ans=this.bucketHashNum.toString().equals(b.getBucketHashNum());
        return ans ;
    }

    protected int findLD()
    {
        return this.ld;
    }
    protected String getBucketHashNum()
    {
        return this.bucketHashNum.toString();
    }
    protected int getBF()
    {
        return this.bf;
    }
    protected int[] getKeys()
    {
        return this.keys;
    }
    protected int getBucketIndex()
    {
        return this.bucketIndex;
    }

}
class BucketData

{
    int bf;
    ArrayList<Bucket> dataBuckets;  
    protected BucketData(int bf, int ld)
    {
        int count = ld-1; 
        this.bf = bf;
        this.dataBuckets = new ArrayList<Bucket>(); 
        Bucket b0 = new Bucket(1, bf, false);
        Bucket b1 = new Bucket(1, bf, true);
        this.dataBuckets.add(b0);  
        this.dataBuckets.add(b1);
        int i=0;
        while(i!=count)
        {
            int length = this.dataBuckets.size(); // arraylist size
             int j=0;
            while(j!=length)
            {
                Bucket b = this.dataBuckets.get(j).bucketSplit();
                this.dataBuckets.add(b);
                j++;
            }
            i++;

        }
        Convert.sortBuckets(this.dataBuckets); // sorting the buckets+
    }


    protected void addBucket(Bucket newBucket)
    {
        this.dataBuckets.add(newBucket);
        Convert.sortBuckets(this.dataBuckets);
    }

    protected void mergeBuckets(Bucket emptyBucket, Bucket mergeToBucket)
    {
        mergeToBucket.merge();
        this.dataBuckets.remove(emptyBucket);
        Convert.sortBuckets(this.dataBuckets);
    }

    protected Bucket getBucket(String bitString)
    {
        for(Bucket b : this.dataBuckets)
        {
            String trimmedVal = Convert.trim(bitString, b.findLD());
            if(trimmedVal.equals(b.getBucketHashNum()))
            {
                return b;
            }
        }
        return null;
    }

    protected Bucket getSplitBucket(Bucket bucket)
    {
        String bname = bucket.getBucketHashNum(); //bname is bucket name
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

    protected int bucketCount()
    {
        return this.dataBuckets.size();
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(Bucket b : this.dataBuckets)
        {
            str.append("|  ");
            int length = 11 - b.bname.length();
            int i=0;

            while(i!=length)
            {
                str.append(" ");
                i++;
            }
            str.append(b.bname + "   ");
            int[] keys = b.getKeys();
            i=0;
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
 class BucketList
 {
    public int gd;
    public int modGrouper;
    private BucketData bucketData;
    private ArrayList<ListRecord> bucketList;

    public String currentBucket, hashvalue;
    
    public BucketList(int gd, int ld, int bf)
    {
        this.gd = gd;


        this.modGrouper = 10;
        this.bucketData = new BucketData(bf, ld);
        this.bucketList = new ArrayList<ListRecord>();
        

        this.generateBucketList();
        System.out.println(Math.pow(2, gd) + " " + this.bucketData.bucketCount());
    }

    public void insertKey(int key)
    {
        int h = key % modGrouper;
        ListRecord r = this.getListRecord(h);
        Bucket b = r.getBucket();
        this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd);
        this.currentBucket = b.toString();
      

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
            Bucket newBucket = b.bucketSplit(modGrouper);
            if(b.getBucketHashNum().equals(Convert.trim(Convert.binaryConversion(h), b.findLD())))
            {
                b.insertKey(key);
            } 
            else
            {
                newBucket.insertKey(key);
            }
            this.bucketData.addBucket(newBucket);
            this.generateBucketList();
        }
    }

    public boolean searchKey(int key)
    {
        int h = key % modGrouper;
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

    public void deleteKey(int key)
    {
        int h = key % modGrouper;
        ListRecord r = this.getListRecord(h);
        Bucket b = r.getBucket();
        this.hashvalue = Convert.trim(Convert.binaryConversion(h), this.gd);
        this.currentBucket = b.toString();
        System.out.println("    Bucket is: " + b);
        if(b.keyChecker(key))
        {
            System.out.println("        Key " + key + " is present in " + b);
            b.deleteKey(key);
            System.out.println("        After deleting:" + b);
            if(b.checkEmpty())
            {
                System.out.println("        " + b + " is empty.");
                Bucket splitBucket = this.bucketData.getSplitBucket(b);
                System.out.println("        " + splitBucket + " is the split bucket.");
                if((splitBucket != null) && (b.canMergeWith(splitBucket)))
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
        for(int i=0; i<Math.pow(2, gd); i++)
        {
            String bitString = Convert.binaryConversion(i);
            this.bucketList.add(new ListRecord(Convert.trim(bitString, gd), bucketData.getBucket(bitString)));
        }
    }
    public String toString()
    {
        StringBuilder str = new StringBuilder("\n");
        for(ListRecord r : this.bucketList)
        {
            str.append(r.toString() + "   \n");
        }
        str.append(" ");
        return str.toString();
    }
 

    public String guiOutput()
    {
        StringBuilder str = new StringBuilder("<br/>");
        for(ListRecord r : this.bucketList)
        {
            str.append(r.toString() + "  <br />");
        }
        str.append("                    ");
        return "<html>" + str.toString() + "<html/>";
    }
  
}
class ListRecord
{
    StringBuilder globalHashValue;
    Bucket bucket;
    
    protected ListRecord(boolean bit, Bucket bucket)
    {
        this.globalHashValue = new StringBuilder();
        if(bit)
        {
            this.globalHashValue.insert(0, 1);
        } else 
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

    public boolean equals(Object O)
    {
        ListRecord dR = (ListRecord)O;
        boolean ans=this.globalHashValue.toString().equals(dR.getGlobalHashValue());
        return ans;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        int length = 10 - this.globalHashValue.length();
        s.append("  ");
        int i=0;
        while(i!=length)
        {
            s.append(" ");
            i++;
        }
        s.append(this.globalHashValue.toString());
        s.append("  |  ");
        s.append(this.bucket.toString());
        return s.toString(); // 3 + 10 + 5 + 11 + 3 = 6 + 15 + 11 = 6 + 26 = 32
    }

    protected String getGlobalHashValue(){
        return this.globalHashValue.toString();
    }
    protected Bucket getBucket(){
        return this.bucket;
    }
}
class Convert
{
    protected static String binaryConversion(int n)
    {
        // Assuming a 10 bit string.
        int num = n;
        StringBuilder bitString = new StringBuilder();
        for(int i=0; i<10; i++)
        {
            bitString.insert(0, num % 2);
            num = num / 2;
        }
        return bitString.toString();
    }

    protected static String trim(String bitString, int depth)
    {
        if(bitString.length() > depth)
        {
            return bitString.substring((bitString.length() - depth), bitString.length());
        }
        else if (bitString.length() == depth){
            return bitString;
        }
        return null;
    }

    protected static void sortBuckets(ArrayList<Bucket> buckets)
    {
        Collections.sort(buckets, new SortBitString());
    }
}

class SortBitString implements Comparator<Bucket>
{
    public int compare(Bucket a, Bucket b)
    {
        return a.getBucketHashNum().compareTo(b.getBucketHashNum());
    }
}
class Module2 {
    public static void main(String[] args) throws IOException {
        System.out.println("                                ***EXTENDIBLE HASHING SIMULATOR***                                    ");
        System.out.println("    ");
        System.out.println(" * Initial Parameters input format : bf m gd ld    ");
        System.out.println("                                     key operation ");
        System.out.println("   where bf-blocking factor, m-number to compute hash, gd-global depth, ld- local depth");
        System.out.println(" * Operations are Insert - I, Delete - D, Search - S");
        System.out.println(" * Type 'exit' to exit the program ");

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bf, m, gd, ld, key;  
        BucketList bucketList = null; 

        System.out.print("> Input Initial Parameters: ");
        String str = br.readLine(); 
        StringTokenizer stoken = new StringTokenizer(str, " "); 
                                                                
        while(stoken.hasMoreTokens())
        {
            bf = Integer.parseInt(stoken.nextToken());
            m = Integer.parseInt(stoken.nextToken());
            gd = Integer.parseInt(stoken.nextToken());
            ld = Integer.parseInt(stoken.nextToken()); // reading 4 inputs
            bucketList = new BucketList(gd, ld, bf); 
            System.out.println(bucketList);  
        }

        

        do{                   
            str = br.readLine();
            if(!str.equals("exit"))
            {
                if(str != null)
                 {   
                        stoken = new StringTokenizer(str, " ");
                        if(stoken.countTokens() < 2) // condition if only one input is given
                        {
                            System.out.println("Please input key and operation both(check format)");
                        } 
                        else 
                        {
                            key = Integer.parseInt(stoken.nextToken()); // first input is key
                            switch(stoken.nextToken())   // checking for the next input
                            {
                                case "I":
                                 {
                                    System.out.println("Inserted " + key + ": ");
                                    bucketList.insertKey(key);
                                    break;
                                 }
                                case "D":
                                {
                                    System.out.println("Deleted " + key + ": ");
                                    bucketList.deleteKey(key);
                                    break;
                                }
                                case "S": 
                                {
                                    System.out.println("Searching key " + key + ": ");
                                    if(bucketList.searchKey(key))
                                    {
                                        System.out.println("Present");
                                    } 
                                    else
                                    {
                                        System.out.println("Absent");
                                    }
                                    break;
                                }
                                
                                default : 
                                {
                                    System.out.println("Not a valid operation");
                                    break;
                                }
                            }
                       }
               }
            }
       System.out.println(bucketList); 
        } while(!str.equals("exit"));

        System.out.println(bucketList);
        System.out.println("After the all Keys are done:\n" + bucketList);
    }
}