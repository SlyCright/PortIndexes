package ports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Port {

    private String[] indexes;

    public int[][] expandIndexesToInt(String[] indexes) {
        int[][] expandedIndexes = new int[indexes.length][];

        for (int i = 0; i < indexes.length; i++) {
            List<String> ranges = Arrays.asList(indexes[i].split(",", 0));
            expandedIndexes[i] = ranges.stream()
                    .map(range -> {
                                List<Integer> subNumbers = new ArrayList<>();
                                List<String> unexpandedNumbers = Arrays.asList(range.split("-", 0));
                                if (unexpandedNumbers.size() == 2) {
                                    int rangeBegin = Integer.parseInt(unexpandedNumbers.get(0));
                                    int rangeEnd = Integer.parseInt(unexpandedNumbers.get(1));
                                    for (int j = rangeBegin; j <= rangeEnd; j++) {
                                        subNumbers.add(j);
                                    }
                                    return subNumbers;
                                }
                                subNumbers.add(Integer.parseInt(unexpandedNumbers.get(0)));
                                return subNumbers;
                            }
                    )
                    .flatMap(Collection::stream)
                    .sorted()
                    .mapToInt(k -> k)
                    .toArray();
        }

        return expandedIndexes;
    }

    public int[][] getCombinedIndexes(int[][] indexes) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination;

        for (int column = 0; column < indexes[0].length; column++) {
            combination = new ArrayList<>();
            combination.add(indexes[0][column]);
            combinations.add(combination);
        }

        for (int row = 1; row < indexes.length; row++) {
            List<List<Integer>> newCombinations = new ArrayList<>();
            for (int column = 0; column < indexes[row].length; column++) {
                int currentIndex = indexes[row][column];
                List<List<Integer>> currentCombinations = combinations.stream()
                        .map(ArrayList::new).collect(Collectors.toList());
                currentCombinations.forEach(c -> c.add(currentIndex));
                newCombinations.addAll(currentCombinations);
            }
            combinations = newCombinations;
        }

        int[][] result = new int[combinations.size()][];

        for (int i = 0; i < combinations.size(); i++) {
            result[i] = combinations.get(i).stream()
                    .mapToInt(x -> x)
                    .toArray();
        }

        return result;
    }

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }

    public String[] getIndexes() {
        return indexes;
    }

}