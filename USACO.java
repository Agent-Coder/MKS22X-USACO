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
      }
      b++;
    }
  }
  private static void stomp(int r, int c, int e){
    int highest=0;
    for (int a=r-1;a<r+2;a++ ) {
      for (int b=c-1;b<c+2;b++ ) {
        if(map[a][b]>highest){
          highest=map[a][b];
        }
      }
    }
    highest=highest-e;
    for (int a=r-1;a<r+2 ;a++ ) {
      for (int b=c-1;b<c+2 ;b++ ) {
        if(map[a][b]>highest){
          map[a][b]=highest;
        }
      }
    }

  }
  private static void depths(){
    for (int a=0;a<R ;a++ ) {
      for (int b=0;b<C ;b++ ) {
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
      stomp(instructions[3*i],instructions[3*i+1],instructions[3*i+2]);
    }
    depths();
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
    int i=0;
    pathway= new int[row][col];
    nextMove= new int[row][col];
    while(i<row){
      one=inf.nextLine().split("",-1);
      for (int j=0;j<col;j++){
        if(one[j].equals("*")){
          pathway[i][j]=-1;
          nextMove[i][j]=-1;
        }
        else{
          pathway[i][j]=0;
          nextMove[i][j]=0;
        }
      }
    i++;
  }
    startCor=new int[2];
    endCor=new int[2];
    one=inf.nextLine().split(" ",-1);
    startCor[0]=Integer.parseInt(one[0])-1;
    startCor[1]=Integer.parseInt(one[1])-1;
    endCor[0]=Integer.parseInt(one[2])-1;
    endCor[1]=Integer.parseInt(one[3])-1;
}
private static String printing(int[][] board){
  String s="";
  for(int i=0;i<row;i++){
    s+="\n";
    for(int j=0;j<col;j++){
      s+=board[i][j]+" ";
    }
  }
  return s;
}
  private static int calculations(int[] m){
    int k=time;
    while(time>=0){
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){

            if(pathway[i][j]!=-1){

              for(int x=0;x<m.length/2;x++){
                if(i+m[2*x]>=0&&i+m[2*x]<row&&j+m[(2*x+1)]>=0&&j+m[(2*x+1)]<col&&pathway[i+m[2*x]][j+m[(2*x+1)]]!=-1){
                  nextMove[i][j]+=pathway[i+m[2*x]][j+m[(2*x+1)]];
                }
              }
            }
          }
        }
        for (int a=0;a<row ;a++ ) {
          for (int b=0;b<col;b++ ){
            if(nextMove[a][b]!=-1){
              pathway[a][b]=nextMove[a][b];
            }
            if(nextMove[a][b]!=-1){
              nextMove[a][b]=0;
            }
          }
        }
        time--;
      }
      return pathway[endCor[0]][endCor[1]];
    }

  public static int silver(String filename) throws FileNotFoundException{
    int[] moves={1,0,-1,0,0,1,0,-1};
    silverFile(filename);
    nextMove[startCor[0]][startCor[1]]=1;
    return calculations(moves);
  }
  /*public static void main(String[] args) {
    try{
    System.out.println(silver("ctravel.1.in"));
    System.out.println("Answer:1");
    System.out.println(silver("ctravel.2.in"));
    System.out.println("74");
    System.out.println(silver("ctravel.3.in"));
    System.out.println("6435");
    System.out.println(silver("ctravel.4.in"));
    System.out.println("339246");
    System.out.println(silver("ctravel.5.in"));
    System.out.println("0");
  }
  catch(FileNotFoundException e){
    System.out.println("file not found");
  }
}*/
}
