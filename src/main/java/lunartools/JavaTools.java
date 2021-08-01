package lunartools;

public class JavaTools {

	/**
	 * Force execution of garbage collector by trying to allocate all memory.
	 * <br>This always fails with an <code>OutOfMemoryError</code>, after GC did it´s work.
	 * <br>Return value is always <code>null</code>. Returning the allocated <code>int[][]</code> is important to prevent some optimizing
	 * algorithm to ignore/eliminate the <code>new</code> command because the array is never used. Now in fact it is used as return
	 * value, however, thanks to the <code>OutOfMemoryError</code> only <code>null</code> is returned.
	 * 
	 * @return null
	 */
	public static int[][] forceGarbageCollector() {
		try {
			long maxMemoryInLongwords=Runtime.getRuntime().maxMemory()>>2;
			if((maxMemoryInLongwords)>Integer.MAX_VALUE-8) {
				return new int[1+(int)(maxMemoryInLongwords/Integer.MAX_VALUE)][Integer.MAX_VALUE-8];
			}else {
				return new int[1][(int)maxMemoryInLongwords];
			}
		} catch (OutOfMemoryError e) {}
		return null;
	}
	
}
