package JSPWEB.CH05;

import java.io.Serializable;

public class Score implements Serializable {

		private String name;
		
		private int kor, eng, mat, tot;
		private double avg;
		private String grad;
		
		public Score()
		{
			name = "noname";
			kor = eng = mat = 0;
		}
		public Score(String name, int kor, int eng, int mat)
		{
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getKor() {
			return kor;
		}
		public void setKor(int kor) {
			this.kor = kor;
		}
		public int getEng() {
			return eng;
		}
		public void setEng(int eng) {
			this.eng = eng;
		}
		public int getMat() {
			return mat;
		}
		public void setMat(int mat) {
			this.mat = mat;
		}
		public int getTot()
		{
			tot = getKor() + getEng() + getMat();
			return tot;
		}
		public double getAvg()
		{
			avg = (double)getTot() / 3;
			return avg;
		}
		public String getGrad()
		{
			switch ((int)getAvg()/10)
			{
			case 10:
			case 9:
				grad = "A"; break;
			case 8:
				grad = "B"; break;
			case 7: 
				grad = "C"; break;
			case 6:
				grad = "D"; break;
			default:
				grad = "F"; break;		
			}		
			return grad;
		}
		
	}

