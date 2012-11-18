/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package aigilas;

import aigilas.skills.SkillInfo;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import sps.core.Logger;
import sps.graphics.Assets;

import java.rmi.server.SkeletonNotFoundException;


public class UITest implements ApplicationListener {

    private Stage stage;
    private Skin skin;

	@Override
	public void create () {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Assets.get().font();
        TextureRegion buttonBg = new TextureRegion(new Texture("assets/graphics/button_bg.png"));

        style.overFontColor = Color.YELLOW;
        style.downFontColor = Color.GREEN;
        style.fontColor = Color.WHITE;
        style.down = new TextureRegionDrawable(buttonBg);
        style.up = new TextureRegionDrawable(buttonBg);
        style.over = new TextureRegionDrawable(buttonBg);

        Table table = new Table();
        table.setFillParent(true);

        final TextButton button1 = new TextButton("Start Game", style);
        button1.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Logger.info("The first button has been activated");
            }
        });
        button1.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(super.keyDown(event, keycode)){
                    button1.notify(new ChangeListener.ChangeEvent(),true);
                }
                return true;
            }
        });

        table.add(button1);

        TextButton button2 = new TextButton("Deploy Nukes", style);
        button2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Logger.info("Deploying the nukes");
            }
        });
        table.add(button2);

        stage.addActor(table);
        stage.setKeyboardFocus(button1);

	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Table.drawDebug(stage);
	}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
	public void resize (int width, int height) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void dispose () {
		stage.dispose();
	}
}
