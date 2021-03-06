package Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GammaCorrectionFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAceptar;
	private double correction;

	public GammaCorrectionFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		correction = 1.0;
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		gbc_panel.insets = new Insets(10,0,0,0);
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Select the gamma exponent to apply: ");
		panel.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, 8.0, 0.01));
		spinner.setPreferredSize(new Dimension(60, (int) spinner.getPreferredSize().getHeight()));
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					spinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Object tmp = spinner.getValue();
				correction = (double)tmp;
			}
		});
		panel.add(spinner);
		
		btnAceptar = new JButton("Accept");
		getRootPane().setDefaultButton(btnAceptar);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnAceptar, gbc_btnNewButton);
	}
	
	public Double getData() {
		return correction;
	}
}
