import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

class Point{
    int x,y;
    public Point(){
        this(0,0);
    }
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}

public class Maze {
    static LinkedList<Point> route = new LinkedList<Point>();
    static int all = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("maze4.txt"));
        int row=input.nextInt();
        int col=input.nextInt();
        char[][] maze = new char[row+2][col+2];
        Point start=new Point();
        Point end=new Point();
        String line="";
        input.nextLine();
        for(int i=0;i<row+2;i++) {
            if(i!=0&&i!=row+1)
                line = input.nextLine();
            for (int j = 0; j < col + 2; j++) {
                if (i == 0 || i == row+1) {
                    maze[i][j]='#';
                }
                else{
                    if(j==0||j==col+1){
                        maze[i][j]='#';
                    }
                    else{
                        maze[i][j]=line.charAt(j-1);
                        if(maze[i][j]=='S') start=new Point(i,j);
                        if(maze[i][j]=='E') end=new Point(i,j);
                    }
                }
            }
        }
        print(maze);
        System.out.println();
        search(maze,start.x,start.y);
        System.out.println("Number of paths: "+all);
    }

    public static void print(char[][] maze){
        for(int i=0;i<maze.length;i++) {
            for (int j = 0; j <maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void search(char[][] maze,int x,int y){
        Point p = new Point(x,y);
        route.add(p);
        if(maze[x][y]=='E'){
            printRoute();
            print(maze);
            route.remove(p);
            System.out.println();
            all++;
            return;
        }
        if(maze[x][y]==' ') maze[x][y]='.';
        if(maze[x][y+1]==' '||maze[x][y+1]=='E'){
            search(maze,x,y+1);
        }
        if(maze[x+1][y]==' '||maze[x+1][y]=='E'){
            search(maze,x+1,y);
        }
        if(maze[x][y-1]==' '||maze[x][y-1]=='E'){
            search(maze,x,y-1);
        }
        if(maze[x-1][y]==' '||maze[x-1][y]=='E'){
            search(maze,x-1,y);
        }
        maze[x][y]=' ';
        route.remove(p);
    }

    public static void printRoute(){
        System.out.print("Distance: "+(route.size()-1)+"\t");
        for(int i=0;i<route.size();i++){
            System.out.print(route.get(i));
            if(i!=route.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
