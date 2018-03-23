#ifndef SIZE
#define SIZE

#include "Vector.hpp"

#include <algorithm>

using namespace std;

class Size : public Vector
{
	public:
		Size( void );
		Size( const double _x, const double _y );
		Size( const pair< double, double > _x_y );

		double Z( void ) const;
		bool Z( const double _z );

		bool operator==( const Size &that );
};

#endif
