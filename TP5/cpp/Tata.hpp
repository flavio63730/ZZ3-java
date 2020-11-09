#ifndef TATA_HPP
#define TATA_HPP

#include <string>
#include "IToto.hpp"
#include "Mom.hpp"

class Tata : public Mom, public IToto {
	private:
		std::string stringPrivate;
		Tata(std::string);
		void setStringPrivate(std::string);
		std::string getStringPrivate();
	protected:
		int intProtected;
		Tata(int);
		void setIntProtected(int);
		int getIntProtected();
	public:
		double doublePublic;
		Tata(double);
		double getDoublePublic();
		void setDoublePublic(double);
}; // Tata

// private implementation
Tata::Tata(std::string) {}
void Tata::setStringPrivate(std::string) {}
std::string Tata::getStringPrivate() { return std::string(); }

// protected implementation
Tata::Tata(int) {}
void Tata::setIntProtected(int) {}
int Tata::getIntProtected() { return int(); }

// public implementation
Tata::Tata(double) {}
double Tata::getDoublePublic() { return double(); }
void Tata::setDoublePublic(double) {}

#endif //TATA_HPP
