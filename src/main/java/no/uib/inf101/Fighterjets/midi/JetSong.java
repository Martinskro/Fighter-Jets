package no.uib.inf101.Fighterjets.midi;

import javax.sound.midi.*;
import java.io.*;

public class JetSong {
    private Sequencer sequencer;

    public void play(String midiFilePath) {
        try {
            // Create a sequencer
            sequencer = MidiSystem.getSequencer();
            if (sequencer == null) {
                System.err.println("Failed to obtain a sequencer");
                return;
            }

            // Open the sequencer
            sequencer.open();

            // Create a sequence from the MIDI file
            Sequence sequence = MidiSystem.getSequence(new File(midiFilePath));

            // Set the sequence for the sequencer
            sequencer.setSequence(sequence);

            // Start playback
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (sequencer != null && sequencer.isRunning()) {
            sequencer.stop();
        }
    }

    public static void main(String[] args) {
        JetSong jetSong = new JetSong();

        // Specify the path to your MIDI file here
        String midiFilePath = "src/main/java/no/uib/inf101/Fighterjets/resources/fighterjets.midi";

        // Play the MIDI file
        jetSong.play(midiFilePath);

        // Wait for a while (e.g., 10 seconds)
        try {
            Thread.sleep(10000); // Adjust the sleep duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop playback
        jetSong.stop();
    }
}
