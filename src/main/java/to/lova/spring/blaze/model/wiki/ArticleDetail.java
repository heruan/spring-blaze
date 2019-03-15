/*-
 * Copyright 2017-2019 Axians SAIV S.p.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
-*/
package to.lova.spring.blaze.model.wiki;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.FlushStrategy;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.view.LocalizedStringView;

@EntityView(WikiArticle.class)
@CreatableEntityView
@UpdatableEntityView(strategy = FlushStrategy.ENTITY)
public interface ArticleDetail extends ArticleSlug {

    void setSlug(String slug);

    LocalizedStringView getTitle();

    void setTitle(LocalizedStringView title);

    LocalizedStringView getContent();

    void setContent(LocalizedStringView content);

}
