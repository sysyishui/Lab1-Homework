/**
 * A class that represents a rational number in normal form where the numerator
 * and the denominator share no common factors and only the numerator
 * can be negative.
 * 
 * @author Charles Hoot 
 * @version 4.0
*/

public class Rational
{
    // PUT PRIVATE DATA FIELDS HERE
    private int numerator;
    private int denominator;
    private int gcd;

    /**
     * The default constructor for objects of class Rational.  Creates the rational number 1.
     */
    public Rational()
    {       
        // ADD CODE TO THE CONSTRUCTOR
        numerator = 1;
        denominator = 1;
        gcd = 0;
    }

    /**
     * The alternate constructor for objects of class Rational.  Creates a rational number equivalent to n/d.
     * @param n The numerator of the rational number.
     * @param d The denominator of the rational number.
     */    
    public Rational(int n, int d) throws ZeroDenominatorException
    {
        if (d == 0) {
            throw new ZeroDenominatorException("Denominator cannot be zero.");
        }
        if (d < 0) {
            n = -n;
            d = -d;
        }
        this.gcd = gcd(Math.abs(n), Math.abs(d)); 
        this.numerator = n / gcd;
        this.denominator = d / gcd;
        
    }
    
    /**
     * Get the value of the Numerator
     *
     * @return     the value of the numerator
     */
    public int getNumerator()
    {
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        return numerator;
    }
    
    /**
     * Get the value of the Denominator
     *
     * @return     the value of the denominator
     */
    public int getDenominator()
    {
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        return denominator;
    }


    /**
     * Negate a rational number r
     * 
     * @return a new rational number that is negation of this number -r
     */    
    public Rational negate()
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
       return new Rational(this.numerator, -this.denominator);
    }


    /**
     * Invert a rational number r 
     * 
     * @return a new rational number that is 1/r.
     */    
    public Rational invert() throws ZeroDenominatorException
    {               

        if (denominator == 0) {
            throw new ZeroDenominatorException("Cannot invert a Rational with a zero denominator");
        }
        if (numerator == 0) {
            throw new ZeroDenominatorException("0 Numerator cannot be inverted");
        }

        return new Rational(this.denominator, this.numerator);
    }





    /**
     * Add two rational numbers
     *
     * @param other the second argument of the add
     * @return a new rational number that is the sum of this and the other rational
     */    
    public Rational add(Rational other)
    {       
        int lcd = this.denominator * other.denominator / gcd(this.denominator, other.denominator);
    
        // Adjust the numerators to the new denominator
        int adjustedNumerator1 = this.numerator * (lcd / this.denominator);
        int adjustedNumerator2 = other.numerator * (lcd / other.denominator);
        
        int resultNumerator = adjustedNumerator1 + adjustedNumerator2;
        
        return new Rational(resultNumerator, lcd);
    }
    
     /**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        // CHANGE THE RETURN    
        int lcd = this.denominator * other.denominator / gcd(this.denominator, other.denominator);
    
        int adjustedNumerator1 = this.numerator * (lcd / this.denominator);
        int adjustedNumerator2 = other.numerator * (lcd / other.denominator);

        int resultNumerator = adjustedNumerator1 - adjustedNumerator2;
        return new Rational(resultNumerator, lcd);
    }

    /**
     * Multiply two rational numbers
     *
     * @param other the second argument of multiply
     * @return a new rational number that is the sum of this object and the other rational.
     */    
    public Rational multiply(Rational other)
    {       
        // ADD NEW CODE AND CHANGE THE RETURN TO SOMETHING APPROPRIATE
        int resultNumerator = this.numerator * other.numerator;
        int resultDenominator = this.denominator * other.denominator;
        
        return new Rational(resultNumerator, resultDenominator);
    }
        
 
     /**
     * Divide this rational number r by another one t
     *
     * @param other the second argument of divide
     * @return a new rational number that is r/t
     */    
    public Rational divide(Rational other)
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        if (other.numerator == 0) {
            throw new ZeroDenominatorException("Cannot divide by a Rational with a zero numerator");
        }
        
        // Create the reciprocal of the other Rational
        Rational reciprocal = other.invert();
        
        // Multiply the current Rational by the reciprocal of the other
        int resultNumerator = this.numerator * reciprocal.numerator;
        int resultDenominator = this.denominator * reciprocal.denominator;
        
        // Return the result in normalized form
        return new Rational(resultNumerator, resultDenominator);
    }
     
      
 /**
     * Put the rational number in normal form where the numerator
     * and the denominator share no common factors.  Guarantee that only the numerator
     * can be negative.
     *
     */
    private void normalize()
    {
        // ADD CODE TO NORMALIZE THE RATIONAL NUMBER
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    
        // Reduce the fraction to its simplest form
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    
        // Ensure only the numerator can be negative
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    
    }
    
    /**
     * Recursively compute the greatest common divisor of two *non-negative* integers
     *
     * @param a the first argument of gcd
     * @param b the second argument of gcd
     * @return the gcd of the two arguments
     */
    private int gcd(int a, int b)
    {
        int result = 0;
        if(a<b)
            result = gcd(b,a);
        else if(b==0)
            result = a;
        else
        {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
   
    
    
}
