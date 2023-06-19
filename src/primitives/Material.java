package primitives;

public class Material
{
	public int nShininess=0;
	public Double3 KD=new Double3 (0,0,0); 
	public Double3 KS=new Double3 (0,0,0);
	public Double3 Kt=new Double3 (0.0); 
	public Double3 Kr=new Double3 (0.0);
	
	/**
	 * @param setKt the setKt to set
	 */
	public Material setKt(Double3 kt) 
	{
		Kt = kt;
		return this;
	}


	/**
	 * @param setKr the setKr to set
	 */
	public Material setKr(Double3 kr) 
	{
		Kr = kr;
		return this;
	}


	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setnShininess(int nShininess) 
	{
		this.nShininess = nShininess;
		return this;
		
	}

	/**
	 * @param kD the kD to set
	 */
	public Material setKD(double kD) 
	{
		KD=new Double3(kD);
		return this;
	}
	public Material setKD(Double3 kD) 
	{
		KD = kD;
		return this;
	}
	/**
	 * @param kS the kS to set
	 */
	public Material setKS(double kS) 
	{
		KS = new Double3(kS);
		return this;
	}	
	
	public Material setKS(Double3 kS) 
	{
		KS = kS;
		return this;
	}	
}
