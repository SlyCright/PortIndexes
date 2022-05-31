import org.junit.jupiter.api.Test;
import ports.Port;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PortTest {

    Port port=new Port();

    @Test
    void givenUnexpandedIndexes_whenExpandIndex_thenSuccess() {
        //Given
        String[] given = {
                "1-5,7,9-11",
                "2,4-6,8"};
        int[][] expected = {
                {1, 2, 3, 4, 5, 7, 9, 10, 11},
                {2, 4, 5, 6, 8}};
        //When
        int[][] actual = port.expandIndexesToInt(given);
        //Then
        assertArrayEquals(expected, actual);
    }

    @Test
    void givenUncombinedIndexes_whenGetCombinedIndexes_thenSuccess(){
        //Given
        int[][] given = {
                {1,3,4,5},
                {2},
                {3,4}};
        int[][] expected={
                {1,2,3},
                {3,2,3},
                {4,2,3},
                {5,2,3},
                {1,2,4},
                {3,2,4},
                {4,2,4},
                {5,2,4}};
        //When
        int[][] actual = port.getCombinedIndexes(given);
        //Then
        assertArrayEquals(expected,actual);
    }

}