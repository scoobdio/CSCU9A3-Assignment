
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  University maintains an ArrayList called cohorts that stores the list of classes of the university.
 *  Your task is to correctly implement the method bodies for:
 * 
 *  protected ArrayList<Cohort> sortMethod(ArrayList<Cohort> list, int blocks, boolean ascending, String attr)
 *	protected boolean checkForCycles(Graph graph, Module node, Map<Module, Boolean> visited, Map<Module, Boolean> beingVisited)
 *  protected Module searchMethod(ArrayList<Cohort> list, String moduleName)
 *  
 *  The above methods are called via public methods of the same name which supply 
 *  the local modules object as a parameter. You can observe calls to these public
 *  methods in the go methods of UniversityTest.java.  
 */
public class University
{
	private ArrayList<Cohort> cohorts;
	private Graph graph;
    public long comparisonCount = 0; // Count of comparisons for quickSort testing
    public long swapCount = 0; // Count of swaps for quickSort testing

	public University()
	{
		cohorts = new ArrayList<Cohort>();
		graph = new Graph();
	}
	
	public void clear()
	{
		for (Cohort v : cohorts)
		{
			v.getBtree().clear();
		}
		cohorts.clear();
		graph = new Graph();
	}
	/**
	 * Add new class/cohort as well as create the graph of prerequisites
	 * @param v the new cohort
	 */
	public void addClass(Cohort v)
	{
		cohorts.add(v);
		if (!v.getModule().getPrerequisites().isEmpty()) {
			for (Module m : v.getModule().getPrerequisites()) {
				graph.insertEdge(v.getModule(), m);
			}
		} else {
			graph.insertNode(v.getModule());
		}
	}
	public ArrayList<Cohort> getCohort()
	{
		return cohorts;
	}
	public Graph getGraph()
	{
		return graph;
	}
	public void describeModuleList()
	{
		for (Cohort v : cohorts)
		{
			System.out.println(v.toString());
			v.describeStudentTree();
		}
	}


	/**
	 * This method calls the tree walk method for a specific cohort
	 * 
	 * @param val The index of the cohort that you want to call the method for
	 * @param type The type of the tree walk - inOrder, preOrder, postOrder, or ownOrder 
	 * 
	 * @return A String with the names of all Students in the tree
	 */
	public String walkTree(int val, String type)
	{
		return cohorts.get(val).walkTree(type);
	}
	
	/**
	 * This method calls the find method for a specific cohort
	 * 
	 * @param val The index of the cohort that you want to call the method for
	 * @param name The Student name to search for
	 * 
	 * @return A reference to the Student that was found or null if no Student found
	 */
	public Student find(int val, String name)
	{
		return cohorts.get(val).find(name);
	}
	
	/**
	 * This method calls the protected checkForCycle to find for cycles in the graph.
	 * 
	 * You should not modify this code.
	 */
    public boolean checkForCycles()
    {
    	Map<Module, Boolean> visited = new HashMap<Module, Boolean>();
    	Map<Module, Boolean> beingVisited = new HashMap<Module, Boolean>();
    	for (Module m : graph.getVertices()) {
    		if (checkForCycles(this.graph, m, visited, beingVisited)) {
    	    	return true;
    		}
    	}
    	return false;
    }
    
	/**
	 * This method should find cycles in directed graphs.
	 *
     * @param graph The graph
	 * @param node the current node
	 * @param visited List of visited nodes
	 * @param beingVisited List of nodes being visited
	 * 
 	 * @return true, if there is a cycle, false otherwise
	 */
    protected boolean checkForCycles(Graph graph, Module node, Map<Module, Boolean> visited, Map<Module, Boolean> beingVisited)
    {
    	if (Boolean.TRUE.equals(beingVisited.get(node))) {
            return true;
        }

        // skip if processed
        if (Boolean.TRUE.equals(visited.get(node))) {
            return true;
        }

        // mark as visited on current path
        beingVisited.put(node, true);

        // recursively check through all adjacent nodes
        for (Module adjacent : graph.getAdj(node)) {
            if (checkForCycles(graph, adjacent, visited, beingVisited))
                return true;
        }

        // finished processing, mark as visited and remove from current path.
        beingVisited.put(node, false);
        visited.put(node, true);
        return false;
    }

	/**
	 * This method calls the protected searchMethod to search for a module
	 * 
	 * You should not modify this code.
	 * 
	 * @param moduleName Property name to be found
	 * @return	The ArrayList 'property' that has been sorted using  sort
	 */
    public Module searchForModule(String moduleName)
    {	
    	this.sortMethod(true, "name"); // sorting before searching
    	return searchForModule(cohorts, moduleName);
    }
    
	/**
	 * This method uses binary search to find the module
	 * (based on the name) in the ArrayList 'cohorts'.
	 * Assumes 'cohorts' is sorted
	 *
     * @param list An ArrayList of Cohorts objects to search
	 * @param moduleName Module name to be found
 	 * @return	The Cohort with moduleName, or null otherwise
	 */
    protected Module searchForModule(ArrayList<Cohort> list, String moduleName)
    {
		int lb = 0;
		int ub = list.size();
        
        while (lb < ub) {
			int mid = (lb + ub) / 2;
			Cohort c = list.get(mid);
			int cmp = c.getModule().getName().compareTo(moduleName);
			if (cmp == 0) {
				return c.getModule();
			} else if (cmp < 0) {
				ub = mid - 1;
			} else {
				lb = mid + 1;
			}
		}
 
        return null;
    }
    
	/**
	 * This method should use specified sort approach to rearrange
	 * the references in the ArrayList 'cohorts' such that they are in 
	 * order according to the attr (attribute) parameter.
	 * If asc is true, this should be ascending order,
	 * if asc is false, this should be descending order.
	 * 
	 * You should not modify this code.
	 * 
	 * @param asc True if the list should be ascending order, false for descending
     * @param attr Attribute (name or code) that will be use during the sorting
	 * @return	The ArrayList 'cohorts' that has been sorted
	 */
    public ArrayList<Cohort> sortMethod(boolean asc, String attr)
    {	
    	ArrayList<Cohort> sorted = new ArrayList<Cohort>(cohorts);
    	return sortMethod(sorted, 3, asc, attr);
    }

    /**
     * Records time taken for sortMethod to be carried out.
     *
     * @param list
     * @param asc
     * @param attr
     * @return
     */
    public long timedSort(ArrayList<Cohort> list, boolean asc, String attr){
        // Resets counters
        comparisonCount = 0;
        swapCount = 0;

        long start = System.nanoTime();
        sortMethod(list, 3, asc, attr);

        System.out.println("Comparisons: " + comparisonCount);
        System.out.println("Swaps: " + swapCount);

        return System.nanoTime() - start;

    }

    /**
	 * This method should use specified sort approach to rearrange
	 * the references in the ArrayList 'cohorts' such that they are in 
	 * order according to the attr (attribute) parameter.
	 * If asc is true, this should be ascending order,
	 * if asc is false, this should be descending order.
	 * 
	 * You should not modify this code.
	 * 
	 * @param list The cohort list to be sorted
	 * @param block_size The size of the blocks
	 * @param attr Attribute (name or code) that will be use during the sorting
	 * @return	The ArrayList 'cohorts' that has been sorted
	 */
    protected ArrayList<Cohort> sortMethod(ArrayList<Cohort> list, int block_size, boolean ascending, String attr)
    {   	
    	quickSort(list, 0, list.size() - 1, ascending, attr);
		return list;
    }

    /**
     * This is a recursive quickSort - the list is split around a pivot (via partition()) and recursively sorts left and right sides.
     * It inherits the sorting order and which attribute is to be sorted from compareCohorts().
     *
     * @param list  list of Cohorts to be sorted
     * @param low   starting index of current subsection
     * @param high  final index of current subsection
     * @param asc   ascending if true, descending if false
     * @param attr  determines which attribute to sort by - "name" or "code"
     */
    private void quickSort(ArrayList<Cohort> list, int low, int high, boolean asc, String attr) {
        if (low < high) {
            int index = partition(list, low, high, asc, attr);
            quickSort(list, low, index - 1, asc, attr);
            quickSort(list, index , high, asc, attr);
        }
    }

    /**
     * Partition step of quicksort - using middle element as the pivot.
     *
     * Elements are reordered so that anything less than the pivot point are moved to the left side.
     * Anything greater than the pivot point are moved to the right side.
     *
     * @param list  list of Cohorts to be sorted
     * @param low   starting index of current subsection
     * @param high  final index of current subsection
     * @param asc   ascending if true, descending if false
     * @param attr  determines which attribute to sort by - "name" or "code"
     * @return  returns the index which separates the left and right partitions
     */
    private int partition(ArrayList<Cohort> list, int low, int high, boolean asc, String attr) {
        Cohort pivot = list.get((low + high) / 2);
        int i = low;
        int j = high;

        while (i <= j) {
            while (compareCohorts(list.get(i), pivot, asc, attr) < 0) i++;
            while (compareCohorts(list.get(j), pivot, asc, attr) > 0) j--;

            if (i <= j) {
                Cohort temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                swapCount++;
                i++;
                j--;
            }
        }
        return i;
    }

    /**
     * Compares two cohorts by either module name or code, in either ascending or descending order
     *
     * @param a     cohort 1
     * @param b     cohort 2
     * @param asc   ascending if true, descending if false
     * @param attr  determines which attribute to sort by - "name" or "code"
     * @return      returns negative if a < b, zero if a = b and positive if a > b
     */
    private int compareCohorts(Cohort a, Cohort b, boolean asc, String attr) {
        comparisonCount++;
        int cmp;
        if (attr.equals("name")) {
            cmp = a.getModule().getName().compareTo(b.getModule().getName());
        } else { // attr is "code"
            cmp = Integer.compare(a.getModule().getCode(), b.getModule().getCode());
        }
        return asc ? cmp : -cmp;
    }

}
