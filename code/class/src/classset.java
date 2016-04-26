/**
 * Created by Fiero on 4/26/16.
 */

import java.util.*;

public class classset {

	public static void main(String args[]) {
		int count[] = {3,3,4,5,2,3};
		Set<Integer> set = new HashSet<Integer>();
		try{
			for(int i = 0; i<5; i++){
				set.add(count[i]);
			}
			System.out.println(set);

			TreeSet sortedSet = new TreeSet<Integer>(set);
			System.out.println("The sorted list is:");
			System.out.println(sortedSet);

			System.out.println("The First element of the set is: "+
					(Integer)sortedSet.first());
			System.out.println("The last element of the set is: "+
					(Integer)sortedSet.last());
		}
		catch(Exception e){}
	}
}