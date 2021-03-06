package v1ch14.synch2;

/**14-9
 * A bank with a number of bank accounts that uses synchronization primitives. 
 * @author Administrator
 *
 */
public class Bank 
{
	private final double[] accounts;
	
	public Bank(int n, double initialBalance)
	{
		accounts = new double[n];
		for(int i=0; i<accounts.length; i++)
			accounts[i] = initialBalance;
	}
	
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException
	{
		while(accounts[from] < amount)
			wait();
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to]   += amount;
		System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
		notifyAll();
	}
	
	public double getTotalBalance()
	{
		double sum = 0;
		
		for(double a : accounts)
			sum += a;
		
		return sum;
	}
	
	public int size()
	{
		return accounts.length;
	}
}
