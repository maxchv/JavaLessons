package ua.itstep.shaptala.examples;

import java.util.*;

public class Application {

	public static void main(String[] args) {
		// ArrayList
		List<String> list = new ArrayList<>();
		
		// ����������
		list.add("������");
		list.add("������");
		list.add("������");
		
		System.out.println("���������: "+list.size());
		// ������� ��������� �������
		// 1-� ������
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+": "+list.get(i));
		}
		// 2-� ������
		for (String item : list) {
			System.out.println(list.indexOf(item) + ": " + item);
		}
		list.add("������");
		System.out.println(list.lastIndexOf("������"));
		
		list.add(1, "���������");
		// 3-� ������
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()) {
			String item = (String) iterator.next();
			System.out.println(">>> "+item);
			if(item.equals("���������")) {
				iterator.remove();
			}
		}
		
		// 3a-� ������
		ListIterator<?> listIterator = list.listIterator(list.size());
		while(listIterator.hasPrevious()) {
			System.out.println(">>>> " + listIterator.previousIndex()
			                           + ": " + listIterator.previous());
		}
		
		// ������ �������� ���������� ��������
		list.set(list.size()-1, "���������");
		
		// �������� ������� � ������� 1 (�������� ��������)
		System.out.println(list.remove(1));		
		// �������� �� ��������
		System.out.println(list.remove("������"));	
		
		
		Collection<String> treeSet = new TreeSet<>();
		treeSet.add("�����");
		treeSet.add("������");
		
		// ���������� ������ ��������
		list.addAll(treeSet);
		
		List<String> list2 = new ArrayList<>(treeSet);
		
		//list.retainAll(list2);
		list.removeAll(list2);
		
		System.out.println(list);
		
		// �������� �� ��� ������ ����� �������� � ������ 
		if(list.containsAll(list2)) {
			System.out.println("� ������ list ����������� ��� ������ ������ list2");
		} else {
			System.out.println("� ������ list �� ����������� ��� ������ ������ list2");
		}
		
		LinkedList<String> linkedList = new LinkedList<>(list);
		list.add("�������");
			
		linkedList.addFirst("�������");
		linkedList.removeLast();
		linkedList.addLast("���������");
		System.out.println(linkedList);
		
		// ��������� ������������� ��� �������� ���������� ��������
		// �� ��������� ������� ���������
		HashSet<String> hashSet = new HashSet<>(linkedList);
		System.out.println(hashSet);
		hashSet.add("������");
		hashSet.add("���������");
		System.out.println(hashSet);

		// ����������� ������� ��������� �� ���� �� ���������� 
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(linkedList);
		System.out.println(linkedHashSet);
		
		// �������������� ���������� ��� ���������� ������
		TreeSet<String> treeSet2 = new TreeSet<>(linkedList);
		treeSet2.add("��������� �������");
		System.out.println(treeSet2);		
		
	}

}
