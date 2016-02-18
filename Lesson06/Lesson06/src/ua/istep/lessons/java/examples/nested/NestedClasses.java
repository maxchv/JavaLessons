package ua.istep.lessons.java.examples.nested;


public class NestedClasses {
	private String dataNested = "������ �������� ������"; 
	String someData = "�������";
	
	// ��������� �����
	protected static class InnerStaticClass {
		static int staticInt;
		static void staticMethod() {
			
		}	
		int nonStaticInt;
		void nonStaticMethod(NestedClasses _this) {
			// ��� ������� � ����� ������������ ������
			//System.out.println(dataNested);
			_this.OuterMethod();
		}
	}
	
	void OuterMethod() {
		System.out.println("OuterMethod");
	}
	
	// ���������� (inner) �����
	class InnerNonStaticClass{
		// �� �������� ����������� ������!!!
		//static int staticInt;
		//static void staticMethod() {			
		//}	
		int nonStaticInt;
		String someData = "����������";
		
		void nonStaticMethod() {
			// ������ ������ � ����� ������������ ������!!!
			System.out.println(dataNested);
			OuterMethod();
			System.out.println(someData);
			System.out.println(this.someData);
			System.out.println(NestedClasses.this.someData);
		}
	}
	
	void usingLocalClass() {
		// ��������� �����
		class LocalClass {
			int data;
			LocalClass(int d) {
				data = d;
			}
			@Override
			public String toString() {				
				return "Local Class: " + data;
			}
		}
		LocalClass obj = new LocalClass(10);
		System.out.println(obj);
	}
	
	public interface BaseMethods {
		void show();
		void doSomething();
	}
	
	public class implementInterface implements BaseMethods {
		@Override
		public void show() {
		}
		@Override
		public void doSomething() {						
		}
	}
	
	public void usingAnonymClasses () {
		BaseMethods link = new implementInterface();
		
		// ��������� �����
		BaseMethods anonymClass = new BaseMethods() {
			@Override
			public void show() {
				System.out.println("Method show");
			}
			
			@Override
			public void doSomething() {
				System.out.println("Method do some");
			}			
		};
		anonymClass.show();
	}
	
	public static void main(String[] args) {
		NestedClasses.InnerStaticClass staticObject = new NestedClasses.InnerStaticClass();
		
		NestedClasses nestedObject = new NestedClasses();
		NestedClasses.InnerNonStaticClass nonStaticObject = nestedObject.new InnerNonStaticClass();
		nonStaticObject.nonStaticInt = 200;
		nonStaticObject.nonStaticMethod();
		
		//usingLocalClass();
		nestedObject.usingLocalClass();
	}
}
