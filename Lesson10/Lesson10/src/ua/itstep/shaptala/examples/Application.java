package ua.itstep.shaptala.examples;

import java.util.*;

public class Application {

	public static void main(String[] args) {
		// ArrayList
		List<String> list = new ArrayList<>();
		
		// добавление
		list.add("Первый");
		list.add("Второй");
		list.add("Третий");
		
		System.out.println("Элементов: "+list.size());
		// перебор элементов массива
		// 1-й способ
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+": "+list.get(i));
		}
		// 2-й способ
		for (String item : list) {
			System.out.println(list.indexOf(item) + ": " + item);
		}
		list.add("Первый");
		System.out.println(list.lastIndexOf("Первый"));
		
		list.add(1, "Удаляемый");
		// 3-й способ
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()) {
			String item = (String) iterator.next();
			System.out.println(">>> "+item);
			if(item.equals("Удаляемый")) {
				iterator.remove();
			}
		}
		
		// 3a-й способ
		ListIterator<?> listIterator = list.listIterator(list.size());
		while(listIterator.hasPrevious()) {
			System.out.println(">>>> " + listIterator.previousIndex()
			                           + ": " + listIterator.previous());
		}
		
		// замена значения последнего элемента
		list.set(list.size()-1, "Четвертый");
		
		// удаление элемета с индеком 1 (воторого элемента)
		System.out.println(list.remove(1));		
		// удаление по значению
		System.out.println(list.remove("Третий"));	
		
		
		Collection<String> treeSet = new TreeSet<>();
		treeSet.add("Пятый");
		treeSet.add("Шестой");
		
		// добавление другой колекции
		list.addAll(treeSet);
		
		List<String> list2 = new ArrayList<>(treeSet);
		
		//list.retainAll(list2);
		list.removeAll(list2);
		
		System.out.println(list);
		
		// входитят ли ВСЕ данные одной колекции в другую 
		if(list.containsAll(list2)) {
			System.out.println("В списке list содержаться ВСЕ данные списка list2");
		} else {
			System.out.println("В списке list НЕ содержаться все данные списка list2");
		}
		
		LinkedList<String> linkedList = new LinkedList<>(list);
		list.add("Седьмой");
			
		linkedList.addFirst("Нулевой");
		linkedList.removeLast();
		linkedList.addLast("Последний");
		System.out.println(linkedList);
		
		// множества предназначены для хранения уникальных значений
		// не сохраняет порядок элементов
		HashSet<String> hashSet = new HashSet<>(linkedList);
		System.out.println(hashSet);
		hashSet.add("Первый");
		hashSet.add("Некоторый");
		System.out.println(hashSet);

		// сохраняется порядок элементов по мере их добавления 
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(linkedList);
		System.out.println(linkedHashSet);
		
		// автоматическая сортировка при добавлении данных
		TreeSet<String> treeSet2 = new TreeSet<>(linkedList);
		treeSet2.add("Очередной элемент");
		System.out.println(treeSet2);		
		
	}

}
