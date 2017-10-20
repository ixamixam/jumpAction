package jp.daisuke.kobayashi.jumpactiongame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends GameObject  {
    // 横幅、高さ
    public static final float ENEMY_WIDTH = 0.5f;
    public static final float ENEMY_HEIGHT = 0.5f;

    // タイプ（通常と動くタイプ）
    public static final int ENEMY_TYPE_STATIC = 0;
    public static final int ENEMY_TYPE_MOVING = 1;
    public static final int ENEMY_TYPE_MOVING2 = 2;

    // 状態（通常と消えた状態）
    public static final int ENEMY_STATE_NORMAL = 0;
    public static final int ENEMY_STATE_VANISH = 1;

    // 速度
    public static final float ENEMY_VELOCITY = 2.0f;
    public static final float ENEMY_VELOCITY_Y = 2.0f;

    int mType;
    int mState;
    float mFirstY;

    public Enemy(int type, Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, float firstY) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        mType = type;
        mFirstY = firstY;
        if (mType == ENEMY_TYPE_MOVING) {
            velocity.x = ENEMY_VELOCITY;
        }
        if (mType == ENEMY_TYPE_MOVING2) {
            velocity.y = ENEMY_VELOCITY_Y;
        }
    }

    // 座標を更新する
    public void update(float deltaTime) {
        if (mType == ENEMY_TYPE_MOVING) {
            //動くやつ
            setX(getX() + velocity.x * deltaTime);

            if (getX() < ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(ENEMY_WIDTH / 2);
            }
            if (getX() > GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2);
            }
        }else if(mType == ENEMY_TYPE_MOVING2){

            setY(getY() + velocity.y * deltaTime);

            if (getY() < mFirstY - 3) {
                velocity.y = -velocity.y;
                setY(mFirstY - 3);
            }
            if (getY() > mFirstY + 3) {
                velocity.y = -velocity.y;
                setY(mFirstY + 3);
            }
        }

    }

    // 消える
    public void vanish() {
        mState = ENEMY_STATE_VANISH;
        setAlpha(0);
        velocity.x = 0;
    }
}