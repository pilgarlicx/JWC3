package com.hiveworkshop.wc3.gui.modeledit.newstuff.manipulator.uv;

import java.awt.geom.Point2D.Double;

import com.hiveworkshop.wc3.gui.modeledit.CoordinateSystem;
import com.hiveworkshop.wc3.gui.modeledit.UndoAction;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.actions.util.GenericRotateAction;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.manipulator.AbstractManipulator;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.uv.TVertexEditor;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionView;
import com.hiveworkshop.wc3.mdl.TVertex;

public class RotateTVertexVerticalManipulator extends AbstractManipulator {
	private final TVertexEditor modelEditor;
	private final SelectionView selectionView;
	private GenericRotateAction rotationAction;

	public RotateTVertexVerticalManipulator(final TVertexEditor modelEditor, final SelectionView selectionView) {
		this.modelEditor = modelEditor;
		this.selectionView = selectionView;
	}

	@Override
	protected void onStart(final Double mouseStart, final byte dim1, final byte dim2) {
		super.onStart(mouseStart, dim1, dim2);
		final TVertex center = selectionView.getUVCenter(modelEditor.getUVLayerIndex());
		rotationAction = modelEditor.beginRotation(center.x, center.y, CoordinateSystem.Util.getUnusedXYZ(dim1, dim2),
				dim2);
	}

	@Override
	public void update(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		final TVertex center = selectionView.getUVCenter(modelEditor.getUVLayerIndex());
		final double radians = computeRotateRadians(mouseStart, mouseEnd, center, dim1, dim2);
		rotationAction.updateRotation(radians);
	}

	@Override
	public UndoAction finish(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		update(mouseStart, mouseEnd, dim1, dim2);
		return rotationAction;
	}

	private double computeRotateRadians(final Double startingClick, final Double endingClick, final TVertex center,
			final byte portFirstXYZ, final byte portSecondXYZ) {
		double radius = selectionView.getCircumscribedSphereRadius(center, modelEditor.getUVLayerIndex());
		if (radius <= 0) {
			radius = 64;
		}
		final double deltaAngle = (endingClick.y - startingClick.y) / radius;
		return deltaAngle;
	}

}
