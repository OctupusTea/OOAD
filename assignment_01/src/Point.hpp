#ifndef POINT
#define POINT

#include "Vector.hpp"

#include <algorithm>

using namespace std;

class Point : public Vector
{
	public:
		Point( void );
		Point( const double _x, const double _y );
		Point( const double _x, const double _y, const double _z );
		Point( const pair< double, double > _x_y );
		Point( const pair< double, double > _x_y, const double _z );

		double Z( void ) const;
		bool Z( const double _z );

		bool operator==( const Point &that ) const;
		bool operator<( const Point &that ) const;
};

#endif
