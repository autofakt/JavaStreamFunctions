import java.util.*;
import java.util.stream.*;

public class Main
{
    /*Function 1: Using streams, write a method that takes in an integer n and 
    returns an array of size n witheach element initialized to the value 1*/
    
    public static int[] createArray(int n){
        return IntStream.range(0, n).map(x->1).toArray();
        }

    /*Function 2: Using Java streams write a method that takes
    in an array of elements and implements the following equation (Sum equation) */
    
    public static int arrayToSum(int[] arr){
        return Arrays.stream(arr).sum();
        }

    /*Function 3: Write a method that takes a 2D array of bytes and returns
    a string representation of it using streams. */
    
    public static String byteToString(byte[][] input){ 
        return Arrays.stream(input).map(Arrays::toString).collect((Collectors.joining(" ")))
        .replace("[", "").replace("]", "").replace(",","");
        }

    /*Function 4: Generate a list of 20 random numbers and then return only the even ones */
    
    public static List<Integer> randomEvenList(){ 
        return new Random().ints(0, 100)
        .distinct()
        .limit(20)
        .boxed()
        .filter(x -> x % 2 == 0)
        .collect(Collectors.toList());
        }

    /*Function 5: Generate 100 random numbers ranging from -100 to 100, then find the average*/
    /* Two versions created*/
    
    public static double randomAverage(){
        DoubleSummaryStatistics stats = new Random().ints(-100,101).mapToDouble(x->(double)x).limit(100).summaryStatistics();
        return stats.getAverage();
        }
        
    public static double randomAverage2(int totalRandoms){ 
            Random random = new Random();
           return  (random.doubles(-100,100).limit(100).sum())/totalRandoms;
           //return random.doubles(-100,100).limit(100).average().getAsDouble(); also works
    }
    
    /*Function 6: Using streams perform vector addition*/
    
    private static double sum(Vector vec1, Vector vec2) {
        return sum(vec1) + sum(vec2);
        }
        
        private static double sum(Vector vec) {
        return vec.stream().mapToDouble(x -> (double) x).sum();
        }
        
    /*Function 7: Using streams perform vector multiplication*/
    
    private static double product(Vector vec1, Vector vec2) {
        return product(vec1) * product(vec2);
        }
        
        private static double product(Vector vec) {
        return vec.stream().mapToDouble(x -> (double) x).sum();
        }
        
    /*Function 8: Write a function that takes in an integer n and returns an 
    n × n identity matrix in the form of an array using only streams.*/
    
    public static int[][] intTo2D(int n){
        return IntStream.range(0, n).mapToObj(i -> IntStream.range(0, n).toArray()).toArray(int[][]::new);
        }
        
    /*Function 9: Iterate through an Java hashmap<String,int>and print out it’s content to a string. */
    
    public static String hashToString(HashMap<String, Integer> myHash)
        {
        return myHash.entrySet().stream()
        .map(e -> "Key : " + e.getKey() + " value : " + String.valueOf(e.getValue()) )
        .reduce("", (a,b) -> a + b+" , ");
        }
        
    /*Function 10: Transform an array of integers into an array of doubles using streams. */
    
    public static double[] intArrToDblArr(int[] ints){
        return Arrays.stream(ints).mapToDouble(x->(double)x).toArray();
        }
    
    /*Function 11: Create a random array of size N */
    public static int[] intRandom1DArray(int size)
         {
            return new Random().ints().limit(size).toArray(); 
         }
    
    /*Function 12: Generate a two dimensional array with set rows/columns of random integers */
    public static int[][] intRand2DArray(int row, int col) 
         { 
             return IntStream.range(0, row).mapToObj(x  
                  -> new Random().ints().limit(col).toArray()
                ) .toArray(int[][]::new) ;  
         }
    
    /*Function 13: Convert two dimensional array of ints to string */    
    public static String int2DArrayToString(int[][] input) 
        { 
             return Arrays.stream(input).map(x 
                -> Arrays.stream(x). mapToObj(i 
                    -> String.format(" % 6d", i)
                   ) .reduce("", (a,b) -> a + b)
               ).reduce("", (a,b) -> a + b+'\n'); 
        }
    
    /*Function 14: Create a ordered array of ints by size */    
    public static int[] orderedArray(int size) 
         {
            return IntStream.range(0, size).toArray(); 
         }
    
    /*Function 15: Create an ordered two dimensional array by rows/columns*/ 
    public static int[][] ordered2DArray(int row, int col) 
         { 
            return IntStream.range(0, row).mapToObj(x  
                 -> IntStream.range(x*10, x*10+col) .toArray()
                ).toArray(int[][]::new);
         }
         
    /*Function 16: Sum an input array of integers*/
    public static int sumArray(int[] input) 
        { 
           return Arrays.stream(input).reduce(0, (a,b) -> a + b); 
        }
    
    /* Function 17: Find the average of input array */
    public static int averageArray(int[] input) 
        { 
           return Arrays.stream(input).reduce(0, (a,b) -> a + b)/input.length; 
        }
    
    /* Function 18: Sum a 2D array */
    public static int sum2DArray(int[][] input) {
           return Arrays.stream(input).mapToInt(x
              ->Arrays.stream(x).reduce(0, (a,b) -> a + b)
             ).reduce(0, (a,b) -> a + b); 
        }
    
    /* Bonus: Replace triple nested loop calculation in function with stream */
    public static double[][] multOLD(double a[][], double b[][]){//a[m][n], b[n][p]
        if(a.length == 0) return new double[0][0];
        if(a[0].length != b.length) return null; //invalid dims
        
        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        
        double ans[][] = new double[m][p];
        
        //  Replaced this
        for(int i = 0;i < m;i++){
        for(int j = 0;j < p;j++){
        for(int k = 0;k < n;k++){
        ans[i][j] += a[i][k] * b[k][j];
        }
        }
        }
        
        return ans;
        }
        
    public static double[][] multNEW(double a[][], double b[][]){//a[m][n], b[n][p]
        if(a.length == 0) return new double[0][0];
        if(a[0].length != b.length) return null; //invalid dims
        
        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        
        double ans[][] = new double[m][p];
        
        // With this
        ans = Arrays.stream(a)
        .map(r ->IntStream.range(0, b[0].length)
        .mapToDouble(i ->IntStream.range(0, b.length).mapToDouble(j -> r[j] * b[j][i]).sum())
        .toArray()).toArray(double[][]::new);
        
        return ans;
        }
        
	public static void main(String[] args) {
		System.out.println("Function 1: " + Arrays.toString(createArray(5)));
		
		System.out.println("Function 2: " + arrayToSum(createArray(5)));
		
		byte[][] arr = { {2,4,127}, {73,9,65}};
		System.out.println("Function 3: " + byteToString(arr));
		
		List<Integer> random = randomEvenList();
		System.out.println("Function 4: " + random);
		
		System.out.println("Function 5: " + randomAverage2(100));
		
		Vector a = new Vector(); Vector b = new Vector();
        a.add(1.0); a.add(2.0); b.add(3.0);

        System.out.println("Function 6: " + sum(a, b));
        
        System.out.println("Function 7: " + product(a, b));
        
        System.out.println("Function 8: " + Arrays.deepToString(intTo2D(5)));
        
        HashMap<String, Integer> customers = new HashMap<String, Integer>();
    		customers.put("John", 1);
    		customers.put("Henry", 2);
    		customers.put("Smith", 3);

		System.out.println("Fuction 9: " + hashToString(customers));
		
		int[] nums = {1,40,3,15,6,19};
	    System.out.println("Function 10: " + Arrays.toString(intArrToDblArr(nums)));
		
		System.out.println("Function 11: " + Arrays.toString(intRandom1DArray(10)));
		
		System.out.println("Function 12: " + Arrays.deepToString(intRand2DArray(3, 3)));
		
		System.out.println("Function 13: \n" + int2DArrayToString(intRand2DArray(3, 3)));
		
		System.out.println("Function 14: " + Arrays.toString(orderedArray(7)));
		
		System.out.println("Function 15: " + Arrays.deepToString(ordered2DArray(5,5)));
		
	    System.out.println("Function 16: " + sumArray(nums));
	    
	    System.out.println("Function 17: " + averageArray(nums));
	    
	    System.out.println("Function 18: " + sum2DArray(ordered2DArray(2,2)));
	    
	    double array1[][] = {{1.0,2.0,3.0},{2.0,1.0,0}, {3.0, 1, 1}};
        double array2[][] = {{1.5,1.0,1.0},{2.0,1.0,2.0}, {3.0, 1, 1}};
        double newArray[][] = multOLD(array1,array2);
        double newArray[][] = multNEW(array1,array2);
	    System.out.println("Bonus: " + Arrays.deepToString(newArray));
	}
}
