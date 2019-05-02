package com.tiket.javabasic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LambdaStatistic {
	public static void main(String args[]) {

		String textKu = "";
		
		File file = new File("LambdaStatistic.txt"); 
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				textKu = textKu.concat(st);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		System.out.println(textKu);
		
		List<String> myList = new ArrayList<String>(Arrays.asList(textKu.toLowerCase()
				.replace(",", "").replace(":", "").replace("-", "").replace("\"", "")
				.replace("(", "").replace(")", "").replace(".", "").replace(";", "")
				.replace("!", "")
				.split(" ")));

		Map<String, Integer> map = myList.parallelStream()
				.collect(Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());

		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2) {
				return m1.getValue().compareTo(m2.getValue());
			}
		});

		System.out.println("===JUMLAH KATA===");
		System.out.println("--> " + list.size() + "\n");

		System.out.println("===LIST===");
		System.out.println("--> " + list + "\n");
		
		Entry<String, Integer> entry; 

		entry = Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue());
		System.out.println("===MAX KATA===");
		System.out.println("--> muncul sebanyak : "+entry.getValue() );
		System.out.println("--> list of words   : "+getAllKeysForValue(map, entry.getValue()));
		System.out.println("");
		
		entry = Collections.min(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue());
		System.out.println("===MIN KATA===");
		System.out.println("--> muncul sebanyak : "+entry.getValue());
		System.out.println("--> list of words   : "+getAllKeysForValue(map, entry.getValue()));
		System.out.println("");
		
	}

	static <K, V> List<K> getAllKeysForValue(Map<K, V> mapOfWords, V value) {
		List<K> listOfKeys = null;
		if (mapOfWords.containsValue(value)) {
			listOfKeys = new ArrayList<>();

			for (Map.Entry<K, V> entry : mapOfWords.entrySet()) {
				if (entry.getValue().equals(value)) {
					listOfKeys.add(entry.getKey());
				}
			}
		}
		return listOfKeys;
	}
}
