import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This UniversityTest class provides different methods of checking the behaviour
 * of the University class and the objects that it uses. It maintains a
 * reference to a University object called 'stirling' and populates it via
 * the loadData method. 
 * 
 * If you run this class via a call to main, main will create
 * a University object and call the go method which produces some useful output that
 * you can analyse.
 * 
 * Implement the unit test methods for
 * 
 * (i) tree walk method
 * (ii) tree find method
 * (iii) list search
 * (iv) list sorting
 * (v) graph loop
 * (vi) check enrolment size
 * 
 */
public class UniversityTest {
	University stirling = new University();
	
	public static void main(String[] args) {
		UniversityTest test = new UniversityTest();
		test.go();
	}

	@BeforeEach
	public void loadData() {
		stirling.clear();
		
		Professor[] prof_list = new Professor[]{ new Professor("Alek Sib", 159, "aleksib@stir.ac.uk", "CSM", 1.0),
				new Professor("Jill Lai", 369, "jl@stir.ac.uk", "CSM", 0.5),
				new Professor("Kevin Jack", 789, "kj@stir.ac.uk", "CSM", 1.0),
				new Professor("Guy F", 654, "gf@stir.ac.uk", "BES/CSM", 0.75),
				new Professor("Loius V", 643, "lv@stir.ac.uk", "CSM", 0.25),
				new Professor("Lett A", 580, "la@stir.ac.uk", "CSM", 0.5),
				new Professor("Stephanie A", 999, "sa@stir.ac.uk", "BES/CSM", 1.0),};
		
		Student[] list = new Student[]{ new Student("John J", 147, "jj@stir.ac.uk"),
			       					    new Student("Kate K", 565, "kk@stir.ac.uk"),
			       					    new Student("Loius L", 6542, "ll@stir.ac.uk"),
			       					    new Student("Zhang Z", 805, "zz@stir.ac.uk"),
			       					    new Student("Xing L", 100, "xl@stir.ac.uk"),
			       					    new Student("Alma A", 753, "aa@stir.ac.uk"),
			       					    new Student("Borba B", 888, "bb@stir.ac.uk"),
			       					    new Student("Zeus L", 001, "zl@stir.ac.uk"),
			       					    new Student("Laura Q", 075, "lq@stir.ac.uk"),
			       					    new Student("Pierre A", 130, "pa@stir.ac.uk"),
			       					    new Student("Jess Z", 010, "jz@stir.ac.uk"),
			       					    new Student("Hugh J", 101, "hj@stir.ac.uk"),
			       					    new Student("Paul P", 654, "pp@stir.ac.uk")}; // include here some MSc Students

		Module alg1 = new Module(147, "Algorithms 1");
		Module alg2 = new Module(199, "Algorithms 2");
		alg2.addPrerequisites(alg1);
		Module alg3 = new Module(201, "Algorithms 3");
		alg3.addPrerequisites(alg1);
		Module dst = new Module(250, "Data Structure");
		dst.addPrerequisites(alg1);
		dst.addPrerequisites(alg2);
		dst.addPrerequisites(alg3);
		Module ds = new Module(101, "Introduction to Data Science", new ArrayList<Module>(Arrays.asList(alg1)));
		Module ml = new Module(301, "Machine Learning");
		Module dl = new Module(401, "Deep Learning");
		dst.addPrerequisites(dl); //
		ds.addPrerequisites(alg1);
		ds.addPrerequisites(dst);
		ml.addPrerequisites(alg2);
		ml.addPrerequisites(alg3);
		ml.addPrerequisites(ds);
		dl.addPrerequisites(alg1);
		dl.addPrerequisites(ml);
		Module nlp = new Module(450, "Natural Language Processing");
		nlp.addPrerequisites(ml);
		nlp.addPrerequisites(dl);
		nlp.addPrerequisites(ds);
		Module cn = new Module(475, "Computer Networks", new ArrayList<Module>(Arrays.asList(alg1)));
		Module g = new Module(501, "Computer Graphics", new ArrayList<Module>(Arrays.asList(alg2)));

		Cohort a1 = new Cohort(dl,
				new BinaryTree(new Student[]{list[0], list[1], list[2], list[5]}), prof_list[0]);
		stirling.addClass(a1);

		Cohort a2 = new Cohort(ml,
				new BinaryTree(new Student[]{list[3], list[4], list[6]}), prof_list[1]);
		stirling.addClass(a2);

		Cohort a3 = new Cohort(alg3,
				new BinaryTree(new Student[]{list[6], list[10], list[11], list[10]}), prof_list[2]);
		stirling.addClass(a3);

		Cohort a4 = new Cohort(ds,
				new BinaryTree(new Student[]{list[5], list[4], list[0], list[11], list[10]}), prof_list[3]);
		stirling.addClass(a4);

		Cohort a5 = new Cohort(dst,
				new BinaryTree(new Student[]{list[0], list[1], list[6], list[7], list[8]}), prof_list[4]);
		stirling.addClass(a5);
		
		Cohort a6 = new Cohort(alg2,
				new BinaryTree(new Student[]{list[2], list[5], list[6], list[9]}), prof_list[5]);
		stirling.addClass(a6);

		Cohort a7 = new Cohort(alg1,
				new BinaryTree(new Student[]{list[7], list[8], list[9], list[10], list[11]}), prof_list[6]);
		stirling.addClass(a7);

	}
	
	/**
	 * Print the contents of the ArrayList 'list' to standard output.
	 * @param list The list to print
	 */
	public void printArrayList(ArrayList<Cohort> list) {
		for (Cohort v:list) {
			System.out.println(v.toString());
		}
	}

	/**
	 * 'go' produces a useful set of output that allows you to see the
	 * initial state of the modules arraylist, the results of doing the
	 * tree walk, an attempt at finding an object in the tree and 
	 * the results of doing a sort and a search. Initially,
	 * only the state of the modules list will be correct. As you add
	 * further functionality, you should see the correct output being
	 * printed via this method. You do not need to modify it but you may
	 * want to add your own checks to confirm your code is working as intended. 
	 */
	public void go() {
		loadData();
		System.out.println("--Module List--");
		stirling.describeModuleList();
		System.out.println();

		System.out.println("\n--Tree Walk--\n" + stirling.walkTree(5, "inOrder"));
		System.out.println("\n--Tree Walk--\n" + stirling.walkTree(5, "custom"));

		System.out.println("\n--Find--");
		Student mf = stirling.find(0, "John J"); // should be found
		if (mf != null) 
			System.out.println("Found: " + mf);
		else
			System.out.println("Could not find John J");
	
		Student aw = stirling.find(1, "Jack J"); // should not be found
		if (aw != null) 
			System.out.println("Found: " + aw);
		else
			System.out.println("Could not find Jack J");

    	System.out.println("\n--Searching module name--");
    	// Reload the data, otherwise it will still be sorted...
    	loadData();
    	Module s = stirling.searchForModule("Natural Language Processing");
		if (s != null) 
			System.out.println("Found module: " + s);
		else
			System.out.println("Could not find module");
    	Module sw = stirling.searchForModule("Introduction to Data Science");
		if (sw != null) 
			System.out.println("Found module: " + sw);
		else
			System.out.println("Could not find module");

    	System.out.println("\n--Sort ascending name--");
    	ArrayList<Cohort> sorted;
    	// Reload the data, otherwise it will still be sorted...
    	loadData();
    	sorted = stirling.sortMethod(true, "name");
    	printArrayList(sorted);	

		System.out.println("\n--Sort descending code--");
    	// Reload the data, otherwise it will still be sorted...
		loadData();
    	sorted = stirling.sortMethod(false, "code");
    	printArrayList(sorted);

	}
	
	/**
	 * A test for the tree walk
     *  Cohort is created and populated with a couple of students.
     *  walkTree is called and assertTrue used to confirm the presence of the student names.
     *  Result is split by , and stored in names[] and assertTrue used to confirm correct positions.
     *  Repeated for customWalk.
	 */
	@Test
	public void walkTest() {
    	Cohort cohort = new Cohort(new Module(1, "Test"), new Professor("Test", 1, "placeholder@stir.ac.uk","CSCU9A3", 1.0));
        cohort.addStudent(new Student("Greg", 1, "greg@stir.ac.uk"));
        cohort.addStudent(new Student("definitelyNotGreg", 1, "definitelynotgreg@stir.ac.uk"));
        cohort.addStudent(new Student("certainlyNotGreg", 1, "certainlynotgreg@stir.ac.uk"));

        String result = cohort.walkTree("inOrder");
        assertTrue(result.contains("Greg"));
        assertTrue(result.contains("definitelyNotGreg"));
        assertTrue(result.contains("certainlyNotGreg"));

        String[] names = result.split(",");
        assertEquals("Greg", names[0]);
        assertEquals("certainlyNotGreg", names[1]);
        assertEquals("definitelyNotGreg", names[2]);

        result = cohort.walkTree("");
        assertTrue(result.contains("Greg"));
        assertTrue(result.contains("definitelyNotGreg"));
        assertTrue(result.contains("certainlyNotGreg"));

        names = result.split(",");
        assertEquals("Greg", names[0]);
        assertEquals("definitelyNotGreg", names[1]);
        assertEquals("certainlyNotGreg", names[2]);

	}

    /**
     *
     */
    @Test
    public void walkTestEmpty() {
        Cohort cohort = new Cohort(new Module(1, "Test"), new Professor("Test", 1, "placeholder@stir.ac.uk","CSCU9A3", 1.0));
        assertEquals("Empty Tree!", cohort.walkTree("inOrder"));
    }
	

	/**
	 * A test for the graph loop
	 */
	@Test
	public void loopSearchTest() {	
    	// TODO
	}
	
	/**
	 * A test for the tree find method
	 */
	@Test
	public void findTest() {

	}

	/**
	 * A test for the tree find method, demonstrating a bug!
	 */
	@Test
	public void findTestBugDemo() {
		stirling.getCohort().get(0).getBtree().printTree(); // Shows that Loius L is in the tree
		assertNotNull(stirling.getCohort().get(0).getBtree().find("Loius L")); // not found!
	}
	
	/**
	 * A test for the search method
	 */
	@Test
	public void searchTest() {	
    	// TODO
	}
	
	
	/**
	 * A test for the sorting method
	 */
	@Test
	public void sortingTest() {
		// TODO
	}

	/**
	 * A test for the sorting method
	 *
	@Test
	public void sortingSpeedTest() {
		// some example code to generate 100 students at random
    	Student[] students = new Student[100];
		Random r = new Random(1);
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(r);
			System.out.println(students[i]);
		}


	}
    */

    @Test
    public void sortingSpeedTest(){
        University uni = new University();
        Professor placeholder = new Professor("Test", 1, "placeholder@stirling.ac.uk", "CSCU9A3", 1.0);
        int[] sizes = { 100000, 200000, 400000, 800000, 1600000};

        for (int n : sizes) {
            ArrayList<Cohort> list = new ArrayList<>();

            Random r = new Random();

            for (int i = 0; i < n; i++) {
                String moduleName = "Module" + r.nextInt(1000000);
                int moduleCode = r.nextInt(1000000);
                Module m = new Module(moduleCode, moduleName);

                Cohort c = new Cohort(m, placeholder);
                list.add(c);
            }

            long time = uni.timedSort(list, true, "name");

            System.out.println("n = " + n + " -> time = " + time + " ns");
        }
    }

	
	/**
	 * A test for the module enrolment
	 */
	@Test
	public void checkModuleEnrolment() {
    	// TODO
	}
}
