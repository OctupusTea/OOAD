#ifndef VECTOR
#define VECTOR

#include <algorithm>

using namespace std;

enum zStat { Z_UNDETERMINED = -1, Z_DISABLED = 0, Z_NOT_SET = 1, Z_SET = 2 };

class Vector
{
	protected:
	//	saves some numeric value for ( x, y, z ) or ( x, y )
		double x;
		double y;
		double z;
	
	//	saves if z is enabled, or if z is set.
		zStat z_stat;

	public:
	//	default constructor with no argument
		Vector( void );
	//	constructor with x and y argument, and a default-valued z
		Vector( const double _x, const double _y, const double _z = 0.0 );
		Vector( const pair< double, double > _x_y, const double z = 0.0 );


	//	Getters and Setters
		double X( void ) const;
		bool X( const double _x );

		double Y( void ) const;
		bool Y( const double _y );

		virtual double Z( void ) const = 0;
		virtual bool Z( const double _z ) = 0;

		pair< double, double > X_Y( void ) const;
		bool X_Y( const double _x, const double _y );
		bool X_Y( const pair< double, double > _x_y );
};

#endif
