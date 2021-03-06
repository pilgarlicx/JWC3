package com.hiveworkshop.wc3.gui.modeledit.newstuff.manipulator.uv;

import java.awt.geom.Point2D.Double;

import com.hiveworkshop.wc3.gui.modeledit.newstuff.uv.TVertexEditor;

public final class MoveTVertexManipulator extends AbstractMoveTVertexManipulator {

	public MoveTVertexManipulator(final TVertexEditor modelEditor) {
		super(modelEditor);
	}

	@Override
	protected void buildMoveVector(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		moveVector.setCoord(dim1, mouseEnd.x - mouseStart.x);
		moveVector.setCoord(dim2, mouseEnd.y - mouseStart.y);
	}

}
