package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GasStation {

    @Test
    public void test(){
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int result = canCompleteCircuit(gas, cost);
        Assertions.assertEquals(3, result);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, toatlCost = 0, currentGasBal = 0, index = -1;

        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            toatlCost += cost[i];
            currentGasBal += (gas[i] - cost[i]);
            if(currentGasBal < 0){
                index = -1;
                currentGasBal = 0;
            }
            else if(index == -1){
                index = i;
            }
        }
        return totalGas >= toatlCost ? index : -1;
    }


    public int canCompleteCircuit_bf(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++){
            if(isPossible(gas, cost, i)){
                return i;
            }
        }
        return -1;
    }

    private boolean isPossible(int[] gas, int[] cost, int i){
        int n = gas.length;
        int currBal = 0;
        while(n >= 0){
            currBal += gas[i];
            if(currBal >= cost[i]){
                currBal -= cost[i];
                i = (i+1) % gas.length;
                n--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
