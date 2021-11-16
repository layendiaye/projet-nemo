package model;

public class AffineTransformer 
{
	double val_intMax;
	double val_intMin;
	double val_extMax;
	double val_extMin;
	double d_val_int;
	double d_val_ext;
	double r;
	double v0;
	public AffineTransformer(int viMin,int viMax, double veMin, double veMax)
	{
		val_intMax=viMax;
		val_intMin=viMin;
		val_extMax= veMax;
		val_extMin= veMin;
		d_val_int=val_intMax-val_intMin;
		d_val_ext=(val_extMax-val_extMin);
		r=d_val_int/d_val_ext;
		//v0=val_intMax/2;
		
	}
	public double transform(double vInt)
	{
		return Math.max(val_extMin,Math.min(val_extMax, 1/r*(vInt-val_intMin)+val_extMin));
		//return 1/r*(vInt-val_intMin)+val_extMin;
	}
	
	public double reverseTransform(double vExt)
	{
		return Math.max(val_intMin, Math.min(val_intMax,r*(vExt-val_extMin)+val_intMin));
		//return r*(vExt-val_extMin)+val_intMin;
	}
	
}


