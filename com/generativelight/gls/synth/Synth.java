package com.generativelight.gls.synth;

import com.generativelight.gls.stage.StageBuilder;
import com.generativelight.gls.stage.Stage;
import com.generativelight.gls.synth.midi.MIDIInput;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * The Manager is an interface between the stage, the MIDI input and the drawing parts.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Synth {

    final static int SLOT_COUNT = 8;

    private ArrayList<Slot> slots;
    private MIDIInput midiInput;
    private Stage stage;
    private PApplet papplet;

    /**
     * Constructor for Creating the whole app.
     * @param papplet the processing parent
     */
    public Synth(PApplet papplet) {

        this.papplet = papplet;

        slots = new ArrayList<>();
        for (int i = 0; i < SLOT_COUNT; i++) {
            slots.add(new Slot(papplet));
        }

        midiInput = new MIDIInput(this);

        stage = StageBuilder.build("C:\\Users\\janne\\Desktop\\stage.json", papplet);
    }

    /**
     * Get a Slot by its channel number.
     * @param index the index or midi channel (1-8)
     * @return the queried Slot
     */
    public Slot getSlot(int index) {
        if (index > 0 && index <= SLOT_COUNT) {
            return slots.get(index-1);
        }
        else return null;
    }

    /**
     * Calles the draw methods of all slots
     */
    public void draw() {
        for (Slot slot : slots) {
            slot.draw();
        }
        stage.updateStage();
    }

    /**
     * Returns the stage.
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

}