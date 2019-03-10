import java.util.*;
import java.io.*;
public class USACO{
  private static int R;
  private static int C;
  private static int E;
  private static int N;
  private static int[][] map;
  private static int[] instructions;
  private static void bronzeFile(String filename)throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String[] one=inf.nextLine().split(" ",-1);
    R=Integer.parseInt(one[0]);
    C=Integer.parseInt(one[1]);
    E=Integer.parseInt(one[2]);
    N=Integer.parseInt(one[3]);
    map=new int[R][C];
    instructions= new int[3*N];
    for (int x=0;x<R;x++) {
      one=inf.nextLine().split(" ",-1);
      for (int y=0;y<C;y++ ) {
        map[x][y]=Integer.parseInt(one[y]);
      }
    }
    int b=0;
    while(inf.hasNextLine()){
      one = inf.nextLine().split(" ",-1);
      for (int a=0;a<3;a++) {
        instructions[3*b+a]=Integer.parseInt(one[a]);
        //System.out.println(instructions[a]);
      }
      b++;
    }
  }
  private static void stomp(int r, int c, int e){
    int highest=0;
    //System.out.println(r+" "+c);
    for (int a=r-1;a<r+2;a++ ) {
      for (int b=c-1;b<c+2;b++ ) {
        if(map[a][b]>highest){
          highest=map[a][b];
        }
      }
    }
    highest=highest-e;
    //System.out.println(highest);
    for (int a=r-1;a<r+2 ;a++ ) {
      //System.out.print("\n");
      for (int b=c-1;b<c+2 ;b++ ) {
        if(map[a][b]>highest){
          map[a][b]=highest;
        }
      }
    }

  }
  private static void depths(){
    for (int a=0;a<R ;a++ ) {
      //System.out.print("\n");
      for (int b=0;b<C ;b++ ) {
        //System.out.print(map[a][b]+" ");
          map[a][b]=E-map[a][b];

      }
    }
  }
  private static int sum(){
    int summation=0;
    for (int a=0;a<R ;a++ ) {

      for (int b=0;b<C ;b++ ) {

          if(map[a][b]>0){
            summation+=map[a][b];
          }
      }
    }
    return summation;
  }
  public static int bronze(String filename)throws FileNotFoundException{
    bronzeFile(filename);
    for (int i=0;i<instructions.length/3;i++ ) {
      //System.out.println(instructions[3*i]+","+instructions[3*i+1]+","+instructions[3*i+2]);
      stomp(instructions[3*i],instructions[3*i+1],instructions[3*i+2]);
      //System.out.println(printing());
    }
    depths();
    //System.out.println(printing());
    //System.out.println(sum());
    return sum()*72*72;
  }
  private static String printing(){
    String s="";
    for (int a=0;a<R ;a++ ) {
        s+="\n";
      for (int b=0;b<C ;b++ ) {
        s+=map[a][b]+" ";
      }
    }
    return s;
  }
//----------------------------------------------------------------------------------------------------
  private static int[][] pathway;
  private static int[][] nextMove;
  private static int row;
  private static int col;
  private static int time;
  private static int[] startCor;
  private static int[] endCor;
  private static void silverFile(String filename)throws FileNotFoundException{
    File text= new File(filename);
    Scanner inf=new Scanner(text);
    String[] one=inf.nextLine().split(" ",-1);
    row=Integer.parseInt(one[0]);
    col=Integer.parseInt(one[1]);
    time=Integer.parseInt(one[2]);
    int j=0;
    pathway= new int[row][col];
    nextMove= new int[row][col];
    while(inf.hasNextLine()){
      one=inf.nextLine().split("",-1);
      for (int i=0;i<col;i++){
        if(i==endCor[0]&&j==endCor[1]){
          pathway[i][j]=1;
          nextMove[i][j]=0;
        }
        else if(one[i]=="*"){
          pathway[i][j]=-1;
          nextMove[i][j]=-1;
        }
        else{
          pathway[i][j]=0;
          nextMove[i][j]=0;
        }
      j++;
    }
    startCor=new int[2];
    endCor=new int[2];
    one=inf.nextLine().split(" ",-1);
    startCor[0]=Integer.parseInt(one[0]);
    startCor[1]=Integer.parseInt(one[1]);
    endCor[0]=Integer.parseInt(one[2]);
    endCor[1]=Integer.parseInt(one[3]);
  }
}
  private static int calculations(){
    int[] moves={1,0,-1,0,0,1,0,-1};
    while(time>0){
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            if(pathway[i][j]!=-1){
              for(int x=0;x<moves.length/2;x++){
                if(i+2*x>=0&&i+2*x<row&&i+(2*x+1)>=0&&i+(2*x+1)<col){
                  nextMove[i][j]+=pathway[i+2*x][2*x+1];
                }
              }
            }
          }
        }
        for (int a=0;a<row ;a++ ) {
          for (int b=0;b<col;b++ ){
            pathway[a][b]=nextMove[a][b];
            if(nextMove[a][b]!=-1){
              nextMove[a][b]=0;
            }
          }
        }
        time--;
      }
      return pathway[endCor[0]][endCor[1]];
    }

/*  public static int silver(String filename) throws FileNotFoundException{
    silverFile(filename);
  }
  public static void main(String[] args) {
    try{
    System.out.println(bronze("makelake.1.in"));
    System.out.println("342144");
    System.out.println(bronze("makelake.2.in"));
    System.out.println("102762432");
    System.out.println(bronze("makelake.3.in"));
    System.out.println("1058992704");
    System.out.println(bronze("makelake.4.in"));
    System.out.println("753121152");
    System.out.println(bronze("makelake.5.in"));
    System.out.println("1028282688");
  }
  catch(FileNotFoundException e){
    System.out.println("file not found");
  }
}*/
}
