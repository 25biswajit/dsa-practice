package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;
/*Given a string A representing an absolute path for a file (Unix-style).
Return the string A after simplifying the absolute path.
Note:
In Unix-style file system:
A period '.' refers to the current directory.
A double period '..' refers to the directory up a level.
Any multiple consecutive slashes '//' are treated as a single slash '/'.
In Simplified Absolute path:
The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path doesn't end with trailing slashes '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Path will not have whitespace characters.
Input 1:
A = "/home/"
Output 1:
"/home"
Input 2:
A = "/a/./b/../../c/"
Output 2:
"/c"*/
public class SimplifyDirectoryPath {
    @Test
    public void test1(){
        String unixPath = "/a/./b/../../c/d/";
        Assertions.assertEquals("/c/d", simplifyPath(unixPath));
    }
    @Test
    public void test2(){
        String unixPath = "/home/";
        Assertions.assertEquals("/home", simplifyPath(unixPath));
    }
    @Test
    public void test3(){
        String unixPath = "/../";
        Assertions.assertEquals("/", simplifyPath(unixPath));
    }

    // TC: O(N), SC: O(N)
    public String simplifyPath(String unixPath){
        String[] paths = unixPath.split("/");
        Stack<String> stack = new Stack<>();
        for(int i=0;i< paths.length; i++){
            String path = paths[i];
            if(path.equals("") || path.equals(".")) continue;
            if(stack.isEmpty()){
                if(!path.equals("..")){
                    stack.push(path);
                }
            }else {
                if(path.equals("..")){
                    stack.pop();
                }else {
                    stack.push(path);
                }
            }
        }
        if(stack.isEmpty()){
            return "/";
        }
        // We might solve using String instead of String Builder, for bigger input many string will be created which leads to TLE
        StringBuilder sbAns = new StringBuilder();
        while (!stack.isEmpty()){
            sbAns.append(stack.pop());
            sbAns.append("/");
        }
        return reverse(sbAns.toString());
    }

    private String reverse(String path) {
        StringBuilder sb = new StringBuilder();
        String[] paths = path.split("/");
        for(int i=paths.length-1;i>=0;i--){
            sb.append("/");
            sb.append(paths[i]);
        }
        return sb.toString();
    }
}
/*
More of a simulation problem.
Break the string along ‘/’ and process the substrings in order one by one. ‘..’ indicates popping an entry unless there is nothing to pop.*/
