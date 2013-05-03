  import java.util.Random;
  import java.util.HashMap;
  
  public class Sparse {
  
    public class SdMatrix{
      int position;
      int value;
      
      SdMatrix(int p, int v){
	position = p;
	value = v;
      }
    }
    
    int TdMatrix [][];
    SdMatrix SingleMatrix[];
    SdMatrix MatrixPair;
    ArrayStack stack;
    
    int seed, limit, size;
    Random rand = new Random(seed);
    
    public Sparse(int s1, int s2, int s3){
      seed = s1;
      limit = s2;
      size = s3;
    }
    
    public int randomNo (){
      return rand.nextInt(100);
    }
    
    public void Non_sparse2D() {
      System.out.println("initial 2D non-sparse matrix:");
      int r;
      int count =0;
      //creates and populates 2D matrix
      TdMatrix = new int[size][size];
      for(int y = 0; y < size; y++){
	for(int x = 0; x < size; x++){
	  r = randomNo();
	  TdMatrix[x][y] = r; 
	   if(TdMatrix[x][y] > 9){
	    System.out.print(" ");
	  }else{
	    System.out.print("  ");
	  }
	  
	  System.out.print(TdMatrix[x][y]);
	}
	System.out.println();
      } 
      System.out.println();
    }
    
    public void sparse2D(){
      System.out.println("initial 2D sparse matrix:");
      for(int y = 0; y < size; y++){
	for(int x = 0; x < size; x++){
	  if(TdMatrix[x][y] > limit){
	    TdMatrix[x][y] = 0;
	  }
	   if(TdMatrix[x][y] > 9){
	    System.out.print(" ");
	  }else{
	    System.out.print("  ");
	  }
	  System.out.print(TdMatrix[x][y]);
	}
	System.out.println();
      }
      System.out.println();
    }
   
    public void printfullMatrix(){
      int pos, val, incr = 0;
      int x, y;
      //initialises all values to 0
      TdMatrix = new int[size][size];
      System.out.println();
      System.out.println("reconstructed 2D sparse matrix:");
      
      //iterates through matrix pairs
      while(SingleMatrix[incr] != null){
	MatrixPair = SingleMatrix[incr];
	
	x = MatrixPair.position % size;
 	y = MatrixPair.position / size;
 	
	TdMatrix[x][y] = MatrixPair.value;
	incr++;
      }
      
      for (y = 0; y < size; y++){
	for (x = 0; x < size; x++){
	  if(TdMatrix[x][y] > 9){
	    System.out.print(" ");
	  }else{
	    System.out.print("  ");
	  }
	  
	  System.out.print(TdMatrix[x][y]);
	}
	System.out.println();
      }
      
    }
    
    public void DisplaySequence(){ 
      int val, pos, incr = 0;
      System.out.print("sequence:");
      while(SingleMatrix[incr] != null){
	 MatrixPair = SingleMatrix[incr];
	 pos = MatrixPair.position;
	 val = MatrixPair.value;
	 System.out.print(" " + pos + " " + val +";"); 
	 incr++;
      }
      System.out.println();
      stack.print();
    }
    
    public void setupStack(){
      stack = new ArrayStack();
    }
    
    public void DisplayStack(){
      int val, pos, incr = 0;

	stack.print();
	//MatrixPair = stack.pop();
//       do{
// 	MatrixPair = stack.pop();
//       }while (MatrixPair != null);
      System.out.println();
    }
   
   public void Conv1D (){
      int val = 0, pos = 0, count = 0;
      //declares array of singleMatrix pairs
     SingleMatrix = new SdMatrix[size];
     
     for(int y = 0; y < size; y++){
      for(int x = 0; x < size; x++){
	if(TdMatrix[x][y] != 0){
	  pos = (y * size + x);
	  val = TdMatrix[x][y];
	  SingleMatrix[count++] = new SdMatrix(pos, val);  
	}
      }
     }
    }
    
    public void reset (int x, int y, int val){
      int pos, incr = 0;
      pos = (y * size + x);
      System.out.println();
      System.out.println("reset (" + x + "," + y + ") to " + val);
      while(SingleMatrix[incr] != null){
	MatrixPair = SingleMatrix[incr];
	  if(MatrixPair.position == pos){
	    stack.push(MatrixPair);
	    MatrixPair.value = val;
	  }
	incr++;
      }
      DisplaySequence();
     }
    
    public void undo (){
    }
    
    public void transpose (){
    }
    
    public static void main(String args[]) {
      int s1 = Integer.parseInt(args[0]);
      int s2 = Integer.parseInt(args[1]);
      int s3 = Integer.parseInt(args[2]);
    
      Sparse sparse = new Sparse(s1, s2, s3);
      sparse.Non_sparse2D();
      sparse.sparse2D();
      sparse.Conv1D();
      sparse.setupStack();
      sparse.DisplaySequence();
      //sparse.DisplayStack();
      //insert hard code after here ---------
      sparse.reset(4,0,99);
      sparse.printfullMatrix();
      /*sparse.reset(2,0,35);
      sparse.reset(2,1,77);
      sparse.undo();
      sparse.printfullMatrix();
      sparse.reset(2,1,0);
      sparse.reset(0,2,22);
      sparse.undo();
      sparse.reset(5,2,55);
      sparse.reset(3,3,63);
      sparse.undo();
      sparse.printfullMatrix();
      //sparse.transposeSequence();
      sparse.printfullMatrix();*/
    }
  }
  
  