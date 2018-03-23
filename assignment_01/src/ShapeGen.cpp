#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Circle.hpp"
#include "Square.hpp"
#include "Triangle.hpp"
#include "ShapeGen.hpp"

#include <algorithm>
#include <iostream>
#include <vector>
#include <cmath>
#include <ctime>

using namespace std;

const vector< Shape* > ShapeGen::shape_list_void = vector< Shape* >( 0 );
const Shape ShapeGen::shape_void = Shape( );

shapeType ShapeGen::Random_Shape( void )
{
	int shape = rand( ) % 3;
	if( shape == 0 )
	{
		return SHAPE_CIRCLE;
	}
	else if( shape == 1 )
	{
		return SHAPE_SQUARE;
	}
	else if( shape == 2 )
	{
		return SHAPE_TRIANGLE;
	}
	else
	{
		return SHAPE_NONE;
	}
}

Point ShapeGen::Random_Point( void )
{
	double x = Random_Float( -FLOAT_RAND_MAX, FLOAT_RAND_MAX );
	double y = Random_Float( -FLOAT_RAND_MAX, FLOAT_RAND_MAX );
	double z = Random_Float( -FLOAT_RAND_MAX, FLOAT_RAND_MAX );
	
	return Point( x, y, z );
}

Size ShapeGen::Random_Size( void )
{
	double x = Random_Float( -FLOAT_RAND_MAX, FLOAT_RAND_MAX );
	double y = Random_Float( -FLOAT_RAND_MAX, FLOAT_RAND_MAX );

	return Size( x, y );
}

double ShapeGen::Random_Float( const double _min, const double _max )
{
	if( _min == _max )
	{
		return _min;
	}
	else
	{
		return ( double )rand( ) / ( double )RAND_MAX * ( _max -  _min ) + _min;
	}
}

int ShapeGen::Random_Interger( const int _min, const int _max )
{
	if( _min ==  _max )
	{
		return _min;
	}
	else
	{
		return ceil( Random_Float( _min - 0.95, _max ) );
	}
}

void ShapeGen::Initialize( const int _gen_count )
{
	srand( random_seed );

	if( _gen_count <= 0 )
	{
		gen_count = Random_Interger( GEN_RAND_MIN, GEN_RAND_MAX );
	}
	else
	{
		gen_count = _gen_count;
	}

	gen_stat = GEN_INITIALIZED;
}

ShapeGen::ShapeGen( const int _gen_count ) : random_seed( time( NULL ) )
{
	Initialize( _gen_count );
}

ShapeGen::ShapeGen( const int _gen_count, const long _random_seed ) : random_seed( _random_seed )
{
	Initialize( _gen_count );
}

const vector< Shape* >& ShapeGen::Generate( void )
{
	if( gen_stat == GEN_UNDETERMINED )
	{
		cout << "ShapeGen not initialized, randomize.\n";
		gen_count = rand( ) % ( GEN_RAND_MAX - GEN_RAND_MIN + 1 ) + GEN_RAND_MIN;
	}

	shape_list.clear( );

	vector< int > z_order_list( gen_count );
	z_order_list[ 0 ] = rand( ) % 10;
	for( int i = 1; i < gen_count; ++i )
	{
		z_order_list[ i ] = z_order_list[ i - 1 ] + rand( ) % 10 + 1;
	}
	random_shuffle( z_order_list.begin( ), z_order_list.end( ) );

	for( int i = 0; i < gen_count; ++i )
	{
		shapeType shape = Random_Shape( );
		Point center = Random_Point( );
		center.Z( z_order_list[ i ] );
		double length = Random_Float( );

		if( shape == SHAPE_CIRCLE )
		{
			shape_list.push_back( new Circle( center, length ) );
		}
		else if( shape == SHAPE_SQUARE )
		{
			shape_list.push_back( new Square( center, length ) );
		}
		else if( shape == SHAPE_TRIANGLE )
		{
			shape_list.push_back( new Triangle( center, length ) );
		}
	}

	gen_stat = GEN_GENERATED;
	return shape_list;
}

void ShapeGen::Randomize( const int _gen_count )
{
	random_seed = time( NULL );

	Initialize( _gen_count );
}

void ShapeGen::Randomize( const int _gen_count, const long _random_seed )
{
	random_seed = _random_seed;

	Initialize( _gen_count );
}

const vector< Shape* >& ShapeGen::Shape_List( void ) const
{
	if( gen_stat != GEN_GENERATED )
	{
		cout << "Random shape list not generated, return empty list.";
		return shape_list_void;
	}

	return shape_list;
}

const Shape& ShapeGen::Shape_List( const size_t index ) const
{
	if( index >= shape_list.size( ) )
	{
		cout << "Index out of bound, return empty reference.";
		return shape_void;
	}

	return *shape_list.at( index );
}
