package dsa.leetcode.selected;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenParenthesis {

    @Test
    public void test(){
        System.out.println( generateParenthesis(2) );
    }


    List<String> result ;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        solve(new StringBuilder(),2*n);
        return result;
    }


    private void solve(StringBuilder sb, int n){
        if(sb.length() == n){
            if(isValid(sb.toString())) {
                result.add(sb.toString());
            }
            return;
        }

        // add open bracket
        sb.append('(');
        solve(sb, n);
        sb.deleteCharAt(sb.length() - 1);

        // add close bracket
        sb.append(')');
        solve(sb, n);
        sb.deleteCharAt(sb.length() - 1);
    }

    public boolean isValid(String str) {
        int sum = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(')
                sum++;
            else
                sum--;
            if (sum < 0)
                return false;
        }
        return sum == 0;
    }
}
