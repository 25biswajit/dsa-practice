package dsa.advance.day47.binarySearch3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodDistribution {
    @Test
    public void test1(){
        int[] population = {40,60};
        int office = 3;
        Assertions.assertEquals(30, maxMinPeopleInOffice(population,office));
    }
    @Test
    public void test2(){
        int[] population = {40,60};
        int office = 5;
        Assertions.assertEquals(20, maxMinPeopleInOffice(population,office));
    }

    public int maxMinPeopleInOffice(int[] population, int office){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < population.length; i++){
            min = Integer.min(min, population[i]);
        }
        int low =  1;
        int high = min;
        int maxMinPeople = 0;
        while (low <= high){
            int mid = (low + high) / 2;
            int ofcReq = deriveOfcRequiredGivenPopulation(population, mid);
            if(ofcReq < office){
                high = mid - 1;
            }else {
                maxMinPeople = mid;
                low = mid + 1;
            }
        }
        return maxMinPeople;
    }

    private int deriveOfcRequiredGivenPopulation(int[] population, int mid) {
        int ofcReq = 0;
        for(int i = 0; i < population.length; i++){
            ofcReq += population[i]/mid;
        }
        return ofcReq;
    }

}
