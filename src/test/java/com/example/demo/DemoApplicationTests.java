package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {

	}

	public int[][] mergeOverlappingIntervals(int[][] intervals) {
		// Write your code here.
		int length = intervals.length;
		if (length == 0 || length == 1)
			return intervals;
		List<Integer[]> res = new ArrayList<>();
		int start = intervals[0][0];
		int end = intervals[0][1];
		Integer pair[] = new Integer[2];
		Arrays.sort(intervals, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] > o2[0])
					return 1;
				else
					return -1;

			}

		});
		for (int i = 1; i < length; i++) {
			if (end < intervals[i][0]) {
				pair[0] = start;
				pair[1] = end;
				start = intervals[i][0];
				end = intervals[i][1];
				res.add(pair);
				pair = new Integer[2];
			} else {
				end = intervals[i][1];
			}
		}
		pair[0] = start;
		pair[1] = end;
		res.add(pair);
		int finIntervals = res.size();
		int finRes[][] = new int[finIntervals][2];
		for (int i = 0; i < finIntervals; i++) {
			finRes[i][0] = res.get(i)[0];
			finRes[i][1] = res.get(i)[1];
		}
		return finRes;
	}

	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		// Write your code here.
		int length1 = arrayOne.size();
		int length2 = arrayTwo.size();
		if (length1 != length2)
			return false;
		if (length1 == 0)
			return true;
		if (!arrayOne.get(0).equals(arrayTwo.get(0)))
			return false;
		List<Integer> leftOne = getLeft(arrayOne, arrayOne.get(0));
		List<Integer> leftTwo = getLeft(arrayTwo, arrayTwo.get(0));
		List<Integer> rightOne = getRight(arrayOne, arrayOne.get(0));
		List<Integer> rightTwo = getRight(arrayTwo, arrayTwo.get(0));
		return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
	}

	private static List<Integer> getLeft(List<Integer> list, int num) {
		return list.stream().skip(1).filter(el -> el < num).collect(Collectors.toList());
	}

	private static List<Integer> getRight(List<Integer> list, int num) {
		return list.stream().skip(1).filter(el -> el >= num).collect(Collectors.toList());
	}

	public static int[] largestRange(int[] array) {
		// Write your code here.
		int length = array.length;
		List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		Map<Integer, Boolean> map = list.stream().collect(Collectors.toMap(el -> el, el -> true));
		int max = 0;
		int res[] = new int[2];
		for (int i = 0; i < length; i++) {
			if (map.get(array[i])) {
				int lowerLimit = findLower(array[i], map);
				int higherLimit = findHigher(array[i], map);
				if ((higherLimit - lowerLimit) > max) {
					res[0] = lowerLimit;
					res[1] = higherLimit;
					max = higherLimit - lowerLimit;
				}
			}
		}
		return new int[] {};
	}

	private static int findHigher(int i, Map<Integer, Boolean> map) {
		while (map.getOrDefault(i, false))
			i++;
		return i - 1;
	}

	private static int findLower(int i, Map<Integer, Boolean> map) {
		// TODO Auto-generated method stub
		while (map.getOrDefault(i, false))
			i--;
		return i + 1;
	}

	public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
		// Write your code here.
		int rows = array.size();
		if (rows == 0)
			return new ArrayList<>();
		int cols = array.get(0).size();
		List<Integer> res = new ArrayList<>();
		int row = 0, col = 0, dir = 0, maxCol = 0, maxRow = 0;
		while (row < rows || col < cols) {
			if (dir == 0) {
				System.out.println("down");

				while (col >= maxCol & row < rows) {
					res.add(array.get(row).get(col));
					System.out.print(array.get(row).get(col) + " ");
					row++;
					col--;
				}
				System.out.println("row" + row);

				if (row == rows) {
					maxCol++;

				}
				col = maxCol;
				row = maxCol == 0 ? row : rows - 1;
				dir = 1;
			} else {
				System.out.println("up");

				while (row >= maxRow && col < cols) {
					res.add(array.get(row).get(col));
					System.out.print(array.get(row).get(col) + " ");
					row--;
					col++;
				}
				System.out.println("col" + col);

				if (col == cols) {
					maxRow++;

				}
				row = maxRow;
				col = maxRow == 0 ? col : cols - 1;
				dir = 0;
			}
		}
		return res;
	}

	public static String caesarCypherEncryptor(String str, int key) {
		// Write your code here.
		Map<Integer, Character> map = new HashMap<>();
		char curr = 'a';
		for (int i = 0; i < 26; i++) {
			map.put(i, curr);
			curr++;
		}
		int size = str.length();
		String res = "";
		for (int i = 0; i < size; i++) {
			char c = str.charAt(i);
			int toMove = (c - 'a' + key) % 26;
			char newC = map.get(toMove);
			res = res + newC;
		}

		return res;
	}

	public void check() throws RuntimeException {

	}

	public static void main(String args[]) {
		DemoApplicationTests app = new DemoApplicationTests();
		app.check();
	}

	public static BinaryTree rightSiblingTree(BinaryTree root) {
		if (root == null)
			return null;
		// Write your code here.
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			BinaryTree pre = null;
			for (int i = 0; i < size; i++) {
				BinaryTree node = queue.poll();
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
				node.right = null;
				if (pre != null)
					pre.right = node;
				pre = node;
			}
		}
		return root;
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree {
		int value;
		BinaryTree left = null;
		BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public int firstNonRepeatingCharacter(String string) {
		// Write your code here.
		int length = string.length();
		Map<Character, Boolean> map = new HashMap<>();
		for (int i = 0; i < length; i++) {
			if (!map.containsKey(string.charAt(i))) {
				map.put(string.charAt(i), true);
			} else
				map.put(string.charAt(i), false);
		}
		int i = 0;
		while (i < length) {
			if (map.get(string.charAt(i)))
				break;
			i++;
		}
		return i;
	}
	  public static int quickselect(int[] array, int k) {
		    // Write your code here.
				int length=array.length;
				PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
				for(int i=0;i<length;i++) {
					heap.offer(array[i]);
					if(heap.size()>k)heap.poll();
					
				}
				
		    return heap.poll();
		  }
	  public List<String> letterCombinations(String digits) {
	        if(digits=="")return new ArrayList<>();
	        List<String> res= new ArrayList<>();
	        Map<Character, String> map= new HashMap<>();
	        map.put('2',"abc");
	        map.put('3',"def");
	        map.put('4',"ghi");
	        map.put('5',"jkl");
	        map.put('6',"mno");
	        map.put('7',"pqrs");
	        map.put('8',"tuv");
	        map.put('9',"wxyz");
	        dfs(digits,0,digits.length(),res,"", map);
	        return res;
	    }

	private void dfs(String digits, int i, int length, List<String> res, String curr, Map<Character, String> map) {
		if(i==length) {
			res.add(curr);
			return;
		}
		char digit= digits.charAt(i);
		String set= map.get(digit);
		for(int index=0;index<set.length();index++) {
			dfs(digits,i+1,length,res,curr+set.charAt(index),map);
		}
	}
	public List<String> findWords(char[][] board, String[] words) {
        int length= words.length;
        int rows=board.length;
        if(length==0 || rows==0)return new ArrayList<>();
        int cols= board[0].length;
        Map<String,Boolean> map = new HashMap<>();
        Map<Character,Boolean> startMap= new HashMap<>();
        for(int i=0;i<length;i++){
            map.put(words[i],true);
            startMap.put(words[i].charAt(0),true);
        }
        List<String> res = new ArrayList<>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j]!='$' && startMap.containsKey(board[i][j])){
                    dfsWords(board,map,rows,cols,i,j,"",res);
                }
            }
        }
        return res.stream().distinct().collect(Collectors.toList());
    }

	private void dfsWords(char[][] board, Map<String, Boolean> map, int rows, int cols, int i, int j, String curr, List<String> res) {
		// TODO Auto-generated method stub
		if(i>=rows || j>=cols || j<0 || i<0 || board[i][j]=='$')return;
		curr=curr+board[i][j];
		if(map.containsKey(curr))res.add(curr);
		char temp=board[i][j];
		board[i][j]='$';
		dfsWords(board,map,rows,cols,i+1,j,curr,res);
		dfsWords(board,map,rows,cols,i-1,j,curr,res);
		dfsWords(board,map,rows,cols,i,j+1,curr,res);
		dfsWords(board,map,rows,cols,i,j-1,curr,res);
		board[i][j]=temp;
	}
}