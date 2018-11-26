package lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LCS_original {

	public void lcs_length(String X, String Y) throws IOException {

		int m = X.length();
		int n = Y.length();

		// ���ڵ��� ���� char�迭 ����
		char[] x = new char[m];
		char[] y = new char[n];

		// ���ڿ��� ���� X,Y�� �迭�� ����
		for (int i = 0; i < x.length; i++) {
			x[i] = (X.charAt(i));
		}
		for (int i = 0; i < y.length; i++) {
			y[i] = (Y.charAt(i));
		}

		int[][] b = new int[m + 1][n + 1]; // ȭ��ǥ����
		int[][] c = new int[m + 1][n + 1]; // ���� ����

		// ù��°�� ��� ���ڿ����� �������� ������
		// 0���� �ʱ�ȭ
		for (int i = 1; i <= m; i++) {
			c[i][0] = 0;
		}
		for (int j = 0; j <= n; j++) {
			c[0][j] = 0;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1; // �밢������ 1 ���Ѱ� ��������
					b[i][j] = 1; // ��
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 2; // ��
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = 3; // ��
				}
			}
		}

		System.out.print("c Matrix\n");
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("\nb Matrix\n");
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
		
		print_LCS(b, x, m, n);
	}
 
	// b : lcs ����� ���ؼ� �̵� ������ ��Ʈ��ŷ �� �� �ִ� ȭ��ǥ�� ��� ���
	// x : ���� ���
	// i,j ����� ũ�Ⱚ�� ������ �ִ� ����
	public void print_LCS(int b[][], char x[], int i, int j) throws IOException {
		File file = new File("C:/Users/Administrator/Desktop/result.txt");
		if (file.exists()) {
			file.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		PrintWriter pw = new PrintWriter(bw, true);
		if (i == 0 || j == 0)
			return;
		if (b[i][j] == 1) {
			print_LCS(b, x, i - 1, j - 1);
			pw.print(x[i - 1]);
			System.out.print(x[i - 1]);
		} else if (b[i][j] == 2) {
			print_LCS(b, x, i - 1, j);
		} else
			print_LCS(b, x, i, j - 1);
		
		bw.close();
		pw.close();
	}

	public static void main(String[] args) throws IOException {

		LCS_original lcs = new LCS_original();
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/LCS_Data.txt"));
		List<String> list = new ArrayList<String>();

		int i = 0;
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			} else {
				list.add(line);
			}
			i++;
		}

		lcs.lcs_length(list.get(1), list.get(3));

		br.close();

	}

}
